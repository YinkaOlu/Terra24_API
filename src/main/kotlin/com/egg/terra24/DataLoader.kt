package com.egg.terra24

import com.egg.terra24.data.entities.CheckpointTemplate
import com.egg.terra24.data.repository.CheckpointTemplateRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val checkpointTemplateRepository: CheckpointTemplateRepository
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val templates = mutableListOf<CheckpointTemplate>()
        repeat(100) {
            templates.add(CheckpointTemplate(title = "Test$it", description = "test_$it"))
        }
        checkpointTemplateRepository.saveAll(templates)
    }
}