package com.egg.terra24.data.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table
@Entity
class Profile(
        @Id
        val id: String = UUID.randomUUID().toString(),
        val userID: String? = null,
        val displayName: String = "",
        val description: String = "",
        val contact: String = ""
)