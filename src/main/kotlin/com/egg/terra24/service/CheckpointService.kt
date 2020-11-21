package com.egg.terra24.service

import com.egg.terra24.data.repository.CheckpointRepository
import com.egg.terra24.data.repository.CheckpointTemplateRepository

interface CheckpointService {
    fun editTemplate(templateId: String)
    fun deleteTempate(templateId: String)
    fun getTemplate(templateID: String)
    fun getTemplates()
    fun createTemplate()

    fun getCheckpoint(id: String)
    fun editCheckpoint(id: String)
    fun deleteCheckpoint(id: String)
    fun getCheckpoints()
    fun createCheckpoint(templateId: String)
}

class CheckpointServiceImpl(
        templateRepository: CheckpointTemplateRepository,
        checkpointRepository: CheckpointRepository
): CheckpointService {
    override fun editTemplate(templateId: String) {
        TODO("Not yet implemented")
    }

    override fun deleteTempate(templateId: String) {
        TODO("Not yet implemented")
    }

    override fun getTemplate(templateID: String) {
        TODO("Not yet implemented")
    }

    override fun getTemplates() {
        TODO("Not yet implemented")
    }

    override fun createTemplate() {
        TODO("Not yet implemented")
    }

    override fun getCheckpoint(id: String) {
        TODO("Not yet implemented")
    }

    override fun editCheckpoint(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteCheckpoint(id: String) {
        TODO("Not yet implemented")
    }

    override fun getCheckpoints() {
        TODO("Not yet implemented")
    }

    override fun createCheckpoint(templateId: String) {
        TODO("Not yet implemented")
    }

}