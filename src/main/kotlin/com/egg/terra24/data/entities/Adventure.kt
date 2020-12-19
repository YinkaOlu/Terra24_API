package com.egg.terra24.data.entities

import com.egg.terra24.data.entities.request.adventure.NewAdventureRequestBody
import java.util.*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
data class Adventure(
        @Id
        @GeneratedValue
        val id: Long? = null,
        var title: String,
        var description: String = "",
        var authorID: String? = null,
        @OneToMany
        var rootCheckpoints: MutableList<Checkpoint> = mutableListOf()
)