package com.egg.terra24.controller

import com.egg.terra24.controller.type.BaseController
import com.egg.terra24.data.entities.Adventure
import com.egg.terra24.data.entities.request.adventure.EditAdventureRequestBody
import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody
import com.egg.terra24.data.repository.AdventureRepository
import com.egg.terra24.data.repository.CheckpointRepository
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import com.egg.terra24.service.AdventureServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
class AdventureController(
        adventureRepository: AdventureRepository,
        checkpointTemplateRepository: CheckpointTemplateRepository,
        checkpointRepository: CheckpointRepository
): BaseController {
    private val adventureService = AdventureServiceImpl(adventureRepository, checkpointTemplateRepository, checkpointRepository)
    @GetMapping("/adventure", params = ["id"])
    fun getAdventure(@RequestParam(name = "id", defaultValue = "") id: String): Adventure? = adventureService.getAdventure(id)

    @PutMapping("/adventure", params = ["id"])
    fun editAdventure(
            @RequestParam(name = "id", defaultValue = "", ) id: String,
            @RequestBody body: EditAdventureRequestBody
    ): Adventure? = adventureService.editAdventure(id, body)

    @DeleteMapping("/adventure", params = ["id"])
    fun deleteAdventure(@RequestParam(name = "id", defaultValue = "") id: String): Unit = adventureService.deleteAdventure(id)

    @GetMapping("/adventures")
    fun getAll(): List<Adventure> = adventureService.getAdventures()

    @PostMapping("/adventures")
    fun createAdventure(
            @RequestBody body: NewAdventureRequestBody,
            @RequestHeader("UserID") userID: String
    ): Adventure? = adventureService.createAdventure(body, userID)
}