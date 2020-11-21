package com.egg.terra24.data.repository

import com.egg.terra24.data.entities.Adventure
import org.springframework.data.jpa.repository.JpaRepository

interface AdventureRepository: JpaRepository<Adventure, String> {
}