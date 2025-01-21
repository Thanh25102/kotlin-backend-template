package com.khipster.template.khipstertemplate.modules.faces

data class ListData(
    val listId: String,
    val userData: String,
    val accountId: String,
    val createTime: String,
    val lastUpdateTime: String
)

data class ListResponse(
    val lists: List<ListData>
)

data class CreateListResponse(
    val listId: String,
    val url: String,
    val externalUrl: String
)

data class CreateListDto(
    val userData: String
)

data class UpdateListDto(
    val listId: String,
    val userData: String
)