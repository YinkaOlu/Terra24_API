package com.egg.terra24.data.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CheckpointResponse(
        @Id
        val id: String = UUID.randomUUID().toString(),
        val type: Type,
        val description: String = ""
) {
    enum class Type {
        TEXT, ASSET_IMAGE, ASSET_VIDEO
    }
}