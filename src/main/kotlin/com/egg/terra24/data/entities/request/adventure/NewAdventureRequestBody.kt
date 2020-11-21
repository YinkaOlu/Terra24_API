package com.egg.terra24.data.entities.request.adventure

import com.egg.terra24.data.entities.CheckpointTemplate

class NewAdventureRequestBody(
        val title: String = "",
        val description: String = "",
        val rootCheckpointIDs: List<String> = listOf(),
        val checkpointTemplates: List<CheckpointTemplate> = listOf()
)