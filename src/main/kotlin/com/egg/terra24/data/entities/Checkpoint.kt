package com.egg.terra24.data.entities

import com.egg.terra24.data.type.UserResponse
import java.util.*
import javax.persistence.*

@Entity
data class Checkpoint(
        @Id
        @GeneratedValue
        val id: Long? = null,
        @OneToOne
        val template: CheckpointTemplate,
        @OneToMany
        var next: MutableList<Checkpoint> = mutableListOf(),
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