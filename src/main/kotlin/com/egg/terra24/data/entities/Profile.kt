package com.egg.terra24.data.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Profile(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val userID: String? = null,
        val displayName: String = "",
        val description: String = "",
        val contact: String = ""
)