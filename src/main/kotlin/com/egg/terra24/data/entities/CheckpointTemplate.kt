package com.egg.terra24.data.entities

import java.util.*
import javax.persistence.*

@Entity
data class CheckpointTemplate (
        @Id
        var id: String = UUID.randomUUID().toString(),
        @ElementCollection
        var tags: MutableList<String> = mutableListOf(),
        var title: String,
        var description: String,
        val created: Date = Date(),
) {
        fun toCheckpoint(parent: Checkpoint? = null, children: List<Checkpoint>? = null): Checkpoint = Checkpoint(
                template = this,
                parent = parent,
                children = children?.toMutableList() ?: mutableListOf()
        )
}