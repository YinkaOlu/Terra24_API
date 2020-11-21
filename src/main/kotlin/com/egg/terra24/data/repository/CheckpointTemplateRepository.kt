package com.egg.terra24.data.repository

import com.egg.terra24.data.entities.Checkpoint
import com.egg.terra24.data.entities.CheckpointTemplate
import org.springframework.data.jpa.repository.JpaRepository

interface CheckpointTemplateRepository: JpaRepository<CheckpointTemplate, String>
interface CheckpointRepository: JpaRepository<Checkpoint, String>
