package com.egg.terra24.service.adventure

import com.egg.terra24.data.entities.Adventure
import com.egg.terra24.data.entities.Checkpoint
import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.adventure.EditAdventureRequestBody
import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody
import com.egg.terra24.data.repository.AdventureRepository
import com.egg.terra24.data.repository.CheckpointRepository
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import com.egg.terra24.data.repository.ProfileRepository
import com.egg.terra24.service.ProfileServiceImpl
import kotlin.math.pow

class AdventureServiceImpl(
    private val adventureRepository: AdventureRepository,
    private val checkpointTemplateRepository: CheckpointTemplateRepository,
    private val checkpointRepository: CheckpointRepository,
    profileRepo: ProfileRepository
): AdventureService {
    private val profileService = ProfileServiceImpl(profileRepo)
    override fun getAdventure(id: String): Adventure?
            = adventureRepository.findById(id)
        .takeIf { it.isPresent && !it.isEmpty }?.get()

    override fun getAdventures(): List<Adventure> = adventureRepository.findAll()

    override fun editAdventure(userId: String, adventureId: String, edit: EditAdventureRequestBody): Adventure? {
        val adventureOptional = adventureRepository.findById(adventureId)
        val userProfile = profileService.getProfile(userId)
        if (!adventureOptional.isEmpty && adventureOptional.isPresent) {
            val adventure = adventureOptional.get()
            edit.addRoots?.let {templates ->
                checkpointTemplateRepository.saveAll(templates)
                val checkpoints = templates.map { it.toCheckpoint(user = userProfile) }
                checkpointRepository.saveAll(checkpoints)
                adventure.rootCheckpoints.addAll(checkpoints)
            }
            edit.removeRoots?.let { rootsToRemove ->
                adventure.rootCheckpoints.removeIf { template ->
                    rootsToRemove.find { it == template.id } != null
                }
            }
            edit.title?.let { adventure.title = it }
            edit.description?.let { adventure.description = it }
            adventureRepository.save(adventure)
            return adventure
        } else {
            return null
        }
    }

    override fun deleteAdventure(id: String): Unit = adventureRepository.deleteById(id)

    override fun generateAdventure(body: NewAdventureRequestBody, userID: String): Adventure {
        val templates = checkpointTemplateRepository.findAll().toTypedArray()
        val adv = Adventure(title = body.title, description = body.description, authorID = userID)
        templates.shuffle()
        var level = 1.0
        val maxLevels = body.levels

        var remainderExists = true
        var previousCheckpoints: MutableList<Checkpoint>? = null
        var remainingTemplates: Array<CheckpointTemplate>? = arrayOf()
        do {
            var levelResults: LevelResult? = null
            if (level > 1) {
                levelResults = getLevelCheckpoints(level, remainingTemplates)
                if (levelResults == null) remainderExists = false
                processResults(levelResults, level, previousCheckpoints)
            } else {
                levelResults = getLevelCheckpoints(level, templates)
                if (levelResults == null) remainderExists = false
                adv.rootCheckpoints = levelResults?.first ?: mutableListOf()
            }
            levelResults?.first?.let {
                previousCheckpoints = it
                checkpointRepository.saveAll(it)
            }

            remainingTemplates = levelResults?.second?.toTypedArray()
            level++
            if (level > maxLevels) break
        } while (remainderExists)

        adventureRepository.save(adv)
        return adv
    }

    private fun processResults(levelResults: LevelResult?, level: Double, previousCheckpoints: MutableList<Checkpoint>?) {
        val maxParents = (3.0).pow(level - 1)
        levelResults?.first?.forEachIndexed { index, checkPoint ->
            val parentIndex = index.rem(maxParents)
            previousCheckpoints?.get(parentIndex.toInt())?.let {
                it.next.add(checkPoint)
            }
        }
    }

    private fun getLevelCheckpoints(level: Double, templates: Array<CheckpointTemplate>?): LevelResult?  {
        if (templates == null) return null
        val maxCheckpoints = 3.0.pow(level)
        var checkPointsInLevel: LevelResult? = null
        return if (templates.isNotEmpty()) {
            val limit = if (templates.size > maxCheckpoints) maxCheckpoints else (templates.size - 1).toDouble()
            checkPointsInLevel = LevelResult(mutableListOf(), mutableListOf())
            for(templateIndex in 0 until limit.toInt()) {
                checkPointsInLevel.first.add(templates[templateIndex].toCheckpoint())
            }
            if (templates.size > maxCheckpoints) {
                checkPointsInLevel.second
                    .addAll(templates.sliceArray(maxCheckpoints.toInt() until templates.size))
            }
            checkPointsInLevel
        }
        else {
            checkPointsInLevel
        }
    }
}