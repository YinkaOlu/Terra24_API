package com.egg.terra24.service.adventure

import com.egg.terra24.data.entities.Adventure
import com.egg.terra24.data.entities.Checkpoint
import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.entities.request.adventure.EditAdventureRequestBody
import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody

interface AdventureService {
    fun getAdventure(id:String): Adventure?
    fun getAdventures(): List<Adventure>
    fun editAdventure(userId: String, adventureId: String, edit: EditAdventureRequestBody): Adventure?
    fun deleteAdventure(id: String)
    fun generateAdventure(body: NewAdventureRequestBody, userID: String = ""): Adventure
}

typealias LevelResult = Pair<MutableList<Checkpoint>, MutableList<CheckpointTemplate>>