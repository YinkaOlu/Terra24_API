package com.egg.terra24.data.entities.request.checkpoint

class ConnectCheckpointRequestBody(
        val parentCheckpointID: String,
        val leafTemplateIDs: List<String>
)