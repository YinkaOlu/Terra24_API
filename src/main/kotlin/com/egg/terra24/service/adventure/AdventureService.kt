package com.egg.terra24.service.adventure

import com.egg.terra24.data.entities.Adventure
import com.egg.terra24.data.entities.Checkpoint
import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.adventure.EditAdventureRequestBody
import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody
import org.springframework.data.domain.Page

interface AdventureService {
    fun getAdventure(id: Long): Adventure?
    fun getAdventures(pageNumber: Long = 0, pageSize: Long = 3): Page<Adventure>
    fun editAdventure(userId: String, adventureId: String, edit: EditAdventureRequestBody): Adventure?
    fun generateAdventure(body: NewAdventureRequestBody, userID: String = ""): Adventure
}

typealias LevelResult = Pair<MutableList<Checkpoint>, MutableList<CheckpointTemplate>>