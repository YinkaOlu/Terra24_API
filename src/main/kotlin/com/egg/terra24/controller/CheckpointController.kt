package com.egg.terra24.controller

import com.egg.terra24.controller.type.BaseController
import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.checkpoint.template.EditCheckpointTemplateBody
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import com.egg.terra24.service.checkpoint.CheckpointServiceImpl
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
class CheckpointController(
        checkpointTemplateRepository: CheckpointTemplateRepository
): BaseController {
    private val checkpointService = CheckpointServiceImpl(checkpointTemplateRepository)
    @PostMapping("$ROOT_API_SUFFIX$TEMPLATE_API_SUFFIX")
    fun saveTemplate(
            @RequestBody body: CheckpointTemplate
    ): CheckpointTemplate = checkpointService.saveTemplate(body)

    @GetMapping("$ROOT_API_SUFFIX$TEMPLATE_API_SUFFIX")
    fun getTemplate(
        @RequestParam(name = TEMPLATE_ID_PARAMETER, defaultValue = "") templateID: String
    ): CheckpointTemplate? = checkpointService.getTemplate(templateID)

    @GetMapping("$ROOT_API_SUFFIX$TEMPLATE_API_SUFFIX/all")
    fun getTemplates(
        @RequestParam(name = PAGE_PARAMETER, defaultValue = "0") pageValue: String
    ): Page<CheckpointTemplate> = checkpointService.getTemplates(pageValue.toInt())

    @DeleteMapping("$ROOT_API_SUFFIX$TEMPLATE_API_SUFFIX", params = [TEMPLATE_ID_PARAMETER])
    fun deleteTemplate(
            @RequestParam(name = TEMPLATE_ID_PARAMETER, defaultValue = "") templateID: String
    ): String {
        checkpointService.deleteTemplate(templateID)
        return "Checkpoint template deleted"
    }

    @DeleteMapping("$ROOT_API_SUFFIX$TEMPLATE_API_SUFFIX/all")
    fun nukeTemplate(): String {
        checkpointService.nukeTemplates()
        return "Templates nuked"
    }

    @PutMapping("$ROOT_API_SUFFIX$TEMPLATE_API_SUFFIX", params = [TEMPLATE_ID_PARAMETER])
    fun editTemplate(
        @RequestParam(name = TEMPLATE_ID_PARAMETER, defaultValue = "") templateID: String,
        @RequestBody body: EditCheckpointTemplateBody
    ): CheckpointTemplate = checkpointService.editTemplate(templateID, body)

    companion object {
        const val ROOT_API_SUFFIX = "/checkpoint"
        const val TEMPLATE_API_SUFFIX = "/template"
        const val TEMPLATE_ID_PARAMETER = "templateID"
        const val PAGE_PARAMETER = "page"
    }
}