package com.egg.terra24.data.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class CheckpointResponse(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val type: Type,
        val description: String = ""
) {
    enum class Type {
        TEXT, ASSET_IMAGE, ASSET_VIDEO
    }
}