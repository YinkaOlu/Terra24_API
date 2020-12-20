package com.egg.terra24.controller

import com.egg.terra24.controller.type.BaseController
import com.egg.terra24.data.entities.Adventure
import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody
import com.egg.terra24.data.repository.AdventureRepository
import com.egg.terra24.data.repository.CheckpointRepository
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import com.egg.terra24.data.repository.ProfileRepository
import com.egg.terra24.service.adventure.AdventureServiceImpl
import org.springframework.data.domain.Page
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
    @GetMapping(API_SUFFIX, params = [ADVENTURE_PARAMETER_NAME])
    fun getAdventure(@RequestParam(name = "adventureID", defaultValue = "") id: String): Adventure? = adventureService.getAdventure(id.toLong())

    @GetMapping(API_SUFFIX)
    fun getAll(
        @RequestParam(name = PAGE_PARAMETER, defaultValue = "0") pageValue: String,
        @RequestParam(name = PAGE_SIZE, defaultValue = "2") pageSize: String
    ): Page<Adventure> = adventureService.getAdventures(pageNumber = pageValue.toLong(), pageSize = pageSize.toLong())

    @PostMapping(API_SUFFIX)
    fun generate(
        @RequestBody body: NewAdventureRequestBody,
        @RequestHeader("UserID") userID: String
    ): Adventure? = adventureService.generateAdventure(body, userID)

    companion object {
        const val API_SUFFIX = "/adventures"
        const val ADVENTURE_PARAMETER_NAME = "adventureID"
        const val PAGE_PARAMETER = "page"
        const val PAGE_SIZE = "pageSize"
    }
}