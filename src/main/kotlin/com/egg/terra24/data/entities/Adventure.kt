package com.egg.terra24.data.entities

import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody
import java.util.*
import javax.persistence.Entity;
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
data class Adventure(
        var title: String,
        var description: String = "",
        var authorID: String? = null,
        @Id
        val id: String = UUID.randomUUID().toString(),
        @OneToMany
        var rootCheckpoints: MutableList<Checkpoint> = mutableListOf()
)