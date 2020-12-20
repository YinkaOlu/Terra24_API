package com.egg.terra24.data.repository

import com.egg.terra24.data.entities.Adventure
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AdventureRepository: JpaRepository<Adventure, String> {
    fun findAdventureById(id: Long): Optional<Adventure?>
    fun deleteAdventureById(id: Long)
}