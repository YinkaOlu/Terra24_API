package com.egg.terra24.controller

import com.egg.terra24.controller.type.BaseController
import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.checkpoint.ConnectCheckpointRequestBody
import com.egg.terra24.data.entities.request.checkpoint.EditCheckpointRequestBody
import com.egg.terra24.data.repository.CheckpointRepository
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import com.egg.terra24.data.repository.ProfileRepository
import com.egg.terra24.service.CheckpointServiceImpl
import org.springframework.web.bind.annotation.*

//@RestController
//class CheckpointController(
//        checkpointTemplateRepository: CheckpointTemplateRepository,
//        checkpointRepository: CheckpointRepository
//): BaseController {
//    private val checkpointService = CheckpointServiceImpl(checkpointTemplateRepository, checkpointRepository)
//    @PostMapping("/checkpoint/template")
//    fun saveTemplate(
//            @RequestBody body: CheckpointTemplate
//    ): CheckpointTemplate = checkpointService.saveTemplate(body)
//
//    @DeleteMapping("/checkpoint/template", params = ["templateID"])
//    fun deleteTemplate(
//            @RequestParam(name = "templateID", defaultValue = "") templateID: String
//    ): Unit = checkpointService.deleteTemplate(templateID)
//
//    @DeleteMapping("/checkpoints", params = ["checkpointID"])
//    fun deleteCheckpoint(
//            @RequestParam(name = "checkpointID", defaultValue = "") checkpointID: String
//    ): Unit = checkpointService.deleteTemplate(checkpointID)
//
//    @PutMapping("/checkpoints", params = ["checkpointID"])
//    fun editCheckpoint(
//            @RequestParam(name = "checkpointID", defaultValue = "") checkpointID: String,
//            @RequestBody body: EditCheckpointRequestBody
//    ): Unit = checkpointService.editCheckpoint(checkpointID, body)
//
//    @PostMapping("/checkpoints/connect")
//    fun connectCheckpoints(
//            @RequestBody body: ConnectCheckpointRequestBody
//    ) = checkpointService.connect(body)
//
//}