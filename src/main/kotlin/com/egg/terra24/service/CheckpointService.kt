package com.egg.terra24.service

import com.egg.terra24.data.entities.Checkpoint
import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.checkpoint.ConnectCheckpointRequestBody
import com.egg.terra24.data.entities.request.checkpoint.EditCheckpointRequestBody
import com.egg.terra24.data.repository.CheckpointRepository
import com.egg.terra24.data.repository.CheckpointTemplateRepository

interface CheckpointService {
    fun deleteTemplate(templateId: String)
    fun saveTemplate(template: CheckpointTemplate): CheckpointTemplate

    fun connect(request: ConnectCheckpointRequestBody): Checkpoint?
    fun deleteCheckpoint(id: String)
    fun editCheckpoint(id: String, edit: EditCheckpointRequestBody)
}

class CheckpointServiceImpl(
        private val templateRepository: CheckpointTemplateRepository,
        private val checkpointRepository: CheckpointRepository
): CheckpointService {
    override fun deleteTemplate(templateId: String) = templateRepository.deleteById(templateId)
    override fun saveTemplate(template: CheckpointTemplate) = templateRepository.save(template)
    override fun connect(request: ConnectCheckpointRequestBody): Checkpoint? {
        return request.leafTemplateIDs.takeIf { it.isNotEmpty() }?.let { templateIDs ->
            val parentOptional = checkpointRepository.findById(request.parentCheckpointID)
            var parent: Checkpoint? = null
            if (parentOptional.isPresent && !parentOptional.isEmpty) {
                parent = parentOptional.get()
            }

            val templates = templateRepository.findAllById(templateIDs)
            val checkpoints = templates.map {
                it.toCheckpoint(user = parent?.user)
            }
            checkpointRepository.saveAll(checkpoints)
            parent?.apply {
                next.addAll(checkpoints)
            }
            return@let parent
        }
    }
    override fun deleteCheckpoint(id: String) = checkpointRepository.deleteById(id)
    override fun editCheckpoint(checkpointID: String, edit: EditCheckpointRequestBody) {
        val checkpointOptional = checkpointRepository.findById(checkpointID)
        if (checkpointOptional.isPresent && !checkpointOptional.isEmpty) {
            val checkpoint = checkpointOptional.get()
            edit.description?.let { checkpoint.description = it }
            edit.title?.let { checkpoint.title = it }
        }
    }
}