package com.egg.terra24.service.checkpoint

import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.checkpoint.template.EditCheckpointTemplateBody
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

class CheckpointServiceImpl(
    private val templateRepository: CheckpointTemplateRepository
): CheckpointService {
    override fun deleteTemplate(templateId: String) = templateRepository.deleteById(templateId)
    override fun nukeTemplates() = templateRepository.deleteAll()
    override fun saveTemplate(template: CheckpointTemplate) = templateRepository.save(template)
    override fun editTemplate(templateId: String, editDetails: EditCheckpointTemplateBody): CheckpointTemplate {
        val template = templateRepository.getOne(templateId)
        editDetails.addTags?.let { template.tags.addAll(it) }
        editDetails.removeTags?.let { template.tags.removeAll(it) }
        editDetails.description?.let { template.description = it }
        editDetails.title?.let { template.title = it }
        return templateRepository.save(template)
    }
    override fun getTemplate(templateId: String): CheckpointTemplate? = templateRepository.findById(templateId).takeIf { it.isPresent && !it.isEmpty }?.get()
    override fun getTemplates(pageNumber: Int, pageSize:Int): Page<CheckpointTemplate> = templateRepository.findAll(
        PageRequest.of(pageNumber, pageSize))
}