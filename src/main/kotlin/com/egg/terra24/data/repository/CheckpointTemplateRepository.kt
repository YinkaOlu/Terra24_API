package com.egg.terra24.data.repository

import com.egg.terra24.data.entities.Checkpoint
import com.egg.terra24.data.entities.CheckpointTemplate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface CheckpointTemplateRepository: JpaRepository<CheckpointTemplate, String> {
    fun findCheckpointTemplateById(id: Long): Optional<CheckpointTemplate>
    fun deleteCheckpointTemplateById(id: Long)
}
interface CheckpointRepository: JpaRepository<Checkpoint, String>
