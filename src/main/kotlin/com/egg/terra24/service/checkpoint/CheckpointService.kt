package com.egg.terra24.service.checkpoint

import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.checkpoint.template.EditCheckpointTemplateBody
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface CheckpointService {
    fun nukeTemplates()
    fun saveTemplate(template: CheckpointTemplate): CheckpointTemplate
    fun editTemplate(templateId: Long, editDetails: EditCheckpointTemplateBody): CheckpointTemplate?
    fun getTemplate(templateId: Long): CheckpointTemplate?
    fun getTemplates(pageNumber: Int = 0, pageSize:Int = 10): Page<CheckpointTemplate>
}