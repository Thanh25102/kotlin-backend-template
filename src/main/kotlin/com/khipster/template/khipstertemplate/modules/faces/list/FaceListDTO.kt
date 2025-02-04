package com.khipster.template.khipstertemplate.modules.faces.list

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnore

data class FaceListCreate(
    val branchId: Long? = null,
    val userData: String? = null,
)

data class LunaListCreateRequest(
    @JsonAlias("user_data") val userData: String? = "",
)

data class LunaListCreateResponse(
    @JsonAlias("list_id") val listId: String? = "",
    @JsonAlias("url") val url: String? = "",
    @JsonAlias("external_url") val externalUrl: String? = "",
)

data class LunaListUpdateRequest(
    @JsonAlias("list_id") var listId: String? = "",
    @JsonAlias("user_data") val userData: String? = "",
)

data class LunaListsCountResponse(
    @JsonAlias("lists_count") val listsCount: Long = 0,
)

data class LunaListsDelRequest(
    @JsonAlias("list_ids") val listIds: List<String> = emptyList(),
)

data class LunaListsResponse(
    val lists: List<LunaListResponse>? = emptyList(),
)

data class LunaListResponse(
    @JsonAlias("list_id") val listId: String? = "",
    @JsonAlias("user_data") val userData: String? = "",
    @JsonAlias("account_id") val accountId: String? = "",
    @JsonAlias("create_time") val createTime: String? = "",
    @JsonAlias("last_update_time") val lastUpdateTime: String? = "",
)
