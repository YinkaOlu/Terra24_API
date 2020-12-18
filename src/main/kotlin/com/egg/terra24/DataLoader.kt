package com.egg.terra24

import com.egg.terra24.data.repository.CheckpointTemplateRepository
import com.egg.terra24.data.seeding.SeedingData
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val checkpointTemplateRepository: CheckpointTemplateRepository
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        checkpointTemplateRepository.saveAll(SeedingData.checkpointTemplates)
    }
}