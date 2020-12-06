package com.egg.terra24.service

import com.egg.terra24.data.entities.Adventure
import com.egg.terra24.data.entities.Checkpoint
import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.adventure.EditAdventureRequestBody
import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody
import com.egg.terra24.data.repository.AdventureRepository
import com.egg.terra24.data.repository.CheckpointRepository
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import com.egg.terra24.data.repository.ProfileRepository
import javax.xml.transform.Templates
import kotlin.math.pow

interface AdventureService {
    fun getAdventure(id:String): Adventure?
    fun getAdventures(): List<Adventure>
    fun editAdventure(userId: String, adventureId: String, edit: EditAdventureRequestBody): Adventure?
    fun deleteAdventure(id: String)
    fun createAdventure(create: NewAdventureRequestBody, userID: String): Adventure
    fun generateAdventure(): Adventure
}

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
                adventure.rootCheckpoints.removeIf {template ->
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
    override fun createAdventure(create: NewAdventureRequestBody, userID: String): Adventure {
        var checkpoints: MutableList<Checkpoint> = mutableListOf()
        val userProfile = profileService.getProfile(userID)
        create.rootCheckpointIDs.takeIf { it.isNotEmpty() }?.let { templateIDs ->
            val templates = checkpointTemplateRepository.findAllById(templateIDs)
            checkpoints = templates.map {
                it.toCheckpoint(user= userProfile)
            }.toMutableList()
            checkpointRepository.saveAll(checkpoints)
        }
        create.rootCheckpointIDs.takeIf { it.isNotEmpty() }?.let { templateIDs ->
            val templates = checkpointTemplateRepository.findAllById(templateIDs)
            checkpoints.addAll(templates.map {
                it.toCheckpoint(user= userProfile)
            })
            checkpointRepository.saveAll(checkpoints)
        }

        create.checkpointTemplates.takeIf { it.isNotEmpty() }?.let { templates ->
            checkpointTemplateRepository.saveAll(templates)
            checkpoints.addAll(templates.map {
                it.toCheckpoint(user= userProfile)
            })
            checkpointRepository.saveAll(checkpoints)
        }
        val adv = Adventure(
                title = create.title,
                description = create.description,
                rootCheckpoints = checkpoints,
                authorID = userID
        )
        adventureRepository.save(adv)
        return adv
    }

    override fun generateAdventure(): Adventure {
        val templates = checkpointTemplateRepository.findAll().toTypedArray()
        val adv = Adventure("Eggcelent Adventures", "aut0generated")
        templates.shuffle()
        var level = 1.0
        val maxLevels = 3.0

        var remainderExists = true
        var previousCheckpoints: MutableList<Checkpoint>? = null
        var remaindingTemplates: Array<CheckpointTemplate>? = arrayOf()
        do {
            var levelResults:LevelResult? = null
            if (level > 1) {
                levelResults = getLevelCheckpoints(level, remaindingTemplates)
                if (levelResults == null) remainderExists = false
                processResults(levelResults, level, previousCheckpoints)
            } else {
                levelResults = getLevelCheckpoints(level, templates)
                if (levelResults == null) remainderExists = false
                adv.rootCheckpoints = levelResults?.first ?: mutableListOf()
//                levelResults?.first?.let { adv.rootCheckpoints = it }
            }
            levelResults?.first?.let {
                previousCheckpoints = it
                checkpointRepository.saveAll(it)
            }

            remaindingTemplates = levelResults?.second?.toTypedArray()
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
                it.children.add(checkPoint)
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

typealias LevelResult = Pair<MutableList<Checkpoint>, MutableList<CheckpointTemplate>>