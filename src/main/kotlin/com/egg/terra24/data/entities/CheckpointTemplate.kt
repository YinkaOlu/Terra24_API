package com.egg.terra24.data.entities

import java.util.*
import javax.persistence.*

@Entity
data class CheckpointTemplate (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        @ElementCollection
        var tags: MutableList<String> = mutableListOf(),
        var title: String,
        var description: String,
        val created: Date = Date(),
        val authorUserId: String = "internal"
) {
        fun toCheckpoint(children: List<Checkpoint>? = null, user: Profile? = null): Checkpoint = Checkpoint(
                template = this,
                next = children?.toMutableList() ?: mutableListOf(),
                user = user
        )
}