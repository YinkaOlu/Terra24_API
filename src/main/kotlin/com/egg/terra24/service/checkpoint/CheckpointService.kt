package com.egg.terra24.service.checkpoint

import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.checkpoint.template.EditCheckpointTemplateBody
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface CheckpointService {
    fun deleteTemplate(templateId: String)
    fun nukeTemplates()
    fun saveTemplate(template: CheckpointTemplate): CheckpointTemplate
    fun editTemplate(templateId: String, editDetails: EditCheckpointTemplateBody): CheckpointTemplate
    fun getTemplate(templateId: String): CheckpointTemplate?
    fun getTemplates(pageNumber: Int = 0, pageSize:Int = 1): Page<CheckpointTemplate>
}