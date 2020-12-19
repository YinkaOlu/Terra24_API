package com.egg.terra24.data.entities.request.checkpoint.template

data class EditCheckpointTemplateBody(
    var removeTags: MutableList<String>?,
    var addTags: MutableList<String>?,
    var title: String?,
    var description: String?,
)