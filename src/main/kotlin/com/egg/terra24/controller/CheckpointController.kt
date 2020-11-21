package com.egg.terra24.controller

import com.egg.terra24.controller.type.BaseController
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import org.springframework.web.bind.annotation.*

@RestController
class CheckpointController(
        private val checkpointTemplateRepository: CheckpointTemplateRepository
): BaseController {

}