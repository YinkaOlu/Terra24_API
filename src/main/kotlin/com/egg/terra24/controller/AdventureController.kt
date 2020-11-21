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

//@RestController
//class AdventureController(
//        adventureRepository: AdventureRepository,
//        checkpointTemplateRepository: CheckpointTemplateRepository,
//        checkpointRepository: CheckpointRepository,
//        profileRepository: ProfileRepository
//): BaseController {
//    private val adventureService = AdventureServiceImpl(
//            adventureRepository,
//            checkpointTemplateRepository,
//            checkpointRepository,
//            profileRepository
//    )
//    @GetMapping("/adventure", params = ["adventureID"])
//    fun getAdventure(@RequestParam(name = "adventureID", defaultValue = "") id: String): Adventure? = adventureService.getAdventure(id)
//
//    @PutMapping("/adventure", params = ["adventureID"])
//    fun editAdventure(
//            @RequestParam(name = "adventureID", defaultValue = "") adventureID: String,
//            @RequestBody body: EditAdventureRequestBody,
//            @RequestHeader("UserID") userID: String
//    ): Adventure? = adventureService.editAdventure(userID, adventureID, body)
//
//    @DeleteMapping("/adventure", params = ["adventureID"])
//    fun deleteAdventure(@RequestParam(name = "adventureID", defaultValue = "") id: String): Unit = adventureService.deleteAdventure(id)
//
//    @GetMapping("/adventures")
//    fun getAll(): Iterable<Adventure> = adventureService.getAdventures()
//
//    @PostMapping("/adventures")
//    fun createAdventure(
//            @RequestBody body: NewAdventureRequestBody,
//            @RequestHeader("UserID") userID: String
//    ): Adventure? = adventureService.createAdventure(body, userID)
//}