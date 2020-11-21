package com.egg.terra24.data.repository

import com.egg.terra24.data.entities.Checkpoint
import com.egg.terra24.data.entities.CheckpointTemplate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface CheckpointTemplateRepository: CrudRepository<CheckpointTemplate, String>
interface CheckpointRepository: CrudRepository<Checkpoint, String>
