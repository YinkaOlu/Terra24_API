package com.egg.terra24.data.repository

import com.egg.terra24.data.entities.Adventure
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface AdventureRepository: CrudRepository<Adventure, String>