package com.egg.terra24.service

import com.egg.terra24.data.entities.Adventure
import com.egg.terra24.data.entities.Checkpoint
import com.egg.terra24.data.entities.request.adventure.EditAdventureRequestBody
import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody
import com.egg.terra24.data.repository.AdventureRepository
import com.egg.terra24.data.repository.CheckpointRepository
import com.egg.terra24.data.repository.CheckpointTemplateRepository

interface AdventureService {
    fun getAdventure(id:String): Adventure?
    fun getAdventures(): List<Adventure>
    fun editAdventure(id: String, edit: EditAdventureRequestBody): Adventure?
    fun deleteAdventure(id: String)
    fun createAdventure(create: NewAdventureRequestBody, userID: String): Adventure
}

class AdventureServiceImpl(
        private val adventureRepository: AdventureRepository,
        private val checkpointTemplateRepository: CheckpointTemplateRepository,
        private val checkpointRepository: CheckpointRepository
): AdventureService {
    override fun getAdventure(id: String): Adventure?
            = adventureRepository.findById(id)
            .takeIf { it.isPresent && !it.isEmpty }?.get()

    override fun getAdventures(): List<Adventure> = adventureRepository.findAll()

    override fun editAdventure(id: String, edit: EditAdventureRequestBody): Adventure? {
        val adventureOptional = adventureRepository.findById(id)
        if (!adventureOptional.isEmpty && adventureOptional.isPresent) {
            val adventure = adventureOptional.get()
            edit.addRoots?.let {templates ->
                checkpointTemplateRepository.saveAll(templates)
                val checkpoints = templates.map { it.toCheckpoint() }
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
        create.rootCheckpointIDs.takeIf { it.isNotEmpty() }?.let { templateIDs ->
            val templates = checkpointTemplateRepository.findAllById(templateIDs)
            checkpoints = templates.map {
                it.toCheckpoint()
            }.toMutableList()
            checkpointRepository.saveAll(checkpoints)
        }
        create.rootCheckpointIDs.takeIf { it.isNotEmpty() }?.let { templateIDs ->
            val templates = checkpointTemplateRepository.findAllById(templateIDs)
            checkpoints.addAll(templates.map {
                it.toCheckpoint()
            })
            checkpointRepository.saveAll(checkpoints)
        }

        create.checkpointTemplates.takeIf { it.isNotEmpty() }?.let { templates ->
            checkpointTemplateRepository.saveAll(templates)
            checkpoints.addAll(templates.map {
                it.toCheckpoint()
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
}