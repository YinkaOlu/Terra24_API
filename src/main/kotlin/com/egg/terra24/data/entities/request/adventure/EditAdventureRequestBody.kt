package com.egg.terra24.data.entities.request.adventure

import com.egg.terra24.data.entities.CheckpointTemplate

class EditAdventureRequestBody(
        val title: String? = null,
        val description: String? = null,
        val addRoots: MutableList<CheckpointTemplate>? = mutableListOf(),
        val removeRoots: MutableList<String>? = mutableListOf(),
) {
}