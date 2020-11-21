package com.egg.terra24.data.entities

import com.egg.terra24.data.type.UserResponse
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
data class Checkpoint(
        @OneToOne
        val template: CheckpointTemplate,
        @Id
        val id: String = UUID.randomUUID().toString(),
        @OneToMany
        var children: MutableList<Checkpoint> = mutableListOf(),
        @OneToOne
        var parent: Checkpoint? = null,
        @OneToOne
        var user: Profile? = null,
        @OneToMany
        var responses: MutableList<CheckpointResponse> = mutableListOf()
) {
        private var overrideDescription: String? = null
        private var overrideTitle: String? = null

        var description: String
                get() = overrideDescription ?: template.description
                set(value) {
                        overrideDescription = value
                }

        var title: String
                get() = overrideTitle ?: template.description
                set(value) {
                        overrideTitle = value
                }
}