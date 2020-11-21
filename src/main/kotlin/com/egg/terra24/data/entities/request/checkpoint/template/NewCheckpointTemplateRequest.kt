package com.egg.terra24.data.entities.request.checkpoint.template

import com.egg.terra24.data.entities.Checkpoint
import java.util.*
import javax.persistence.Id


class NewCheckpointTemplateRequest(
        @Id
        val id: String = UUID.randomUUID().toString(),
        val parent: Checkpoint? = null,
        val children: List<Checkpoint> = listOf(),
        val title: String? = null,
        val description: String? = null
)