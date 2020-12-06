package com.egg.terra24.controller

import com.egg.terra24.controller.type.BaseController
import com.egg.terra24.data.entities.Adventure
import com.egg.terra24.data.entities.request.adventure.EditAdventureRequestBody
import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody
import com.egg.terra24.data.repository.AdventureRepository
import com.egg.terra24.data.repository.CheckpointRepository
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import com.egg.terra24.data.repository.ProfileRepository
import com.egg.terra24.service.AdventureServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
class AdventureController(
        adventureRepository: AdventureRepository,
        checkpointTemplateRepository: CheckpointTemplateRepository,
        checkpointRepository: CheckpointRepository,
        profileRepository: ProfileRepository
): BaseController {
    private val adventureService = AdventureServiceImpl(
            adventureRepository,
            checkpointTemplateRepository,
            checkpointRepository,
            profileRepository
    )
    @GetMapping("$API_SUFFIX", params = ["adventureID"])
    fun getAdventure(@RequestParam(name = "adventureID", defaultValue = "") id: String): Adventure? = adventureService.getAdventure(id)

    @PutMapping("$API_SUFFIX", params = ["adventureID"])
    fun editAdventure(
            @RequestParam(name = "adventureID", defaultValue = "") adventureID: String,
            @RequestBody body: EditAdventureRequestBody,
            @RequestHeader("UserID") userID: String
    ): Adventure? = adventureService.editAdventure(userID, adventureID, body)

    @DeleteMapping("$API_SUFFIX", params = ["adventureID"])
    fun deleteAdventure(@RequestParam(name = "adventureID", defaultValue = "") id: String): Unit = adventureService.deleteAdventure(id)

    @GetMapping("$API_SUFFIX")
    fun getAll(): List<Adventure> = adventureService.getAdventures()

    @PostMapping("$API_SUFFIX")
    fun generate(
        @RequestBody body: NewAdventureRequestBody,
        @RequestHeader("UserID") userID: String
    ): Adventure? = adventureService.generateAdventure(body, userID)

    companion object {
        const val API_SUFFIX = "/adventures"
    }
}