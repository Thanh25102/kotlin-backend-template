package com.khipster.template.khipstertemplate.modules.faces.users

import com.fasterxml.jackson.annotation.JsonAlias

data class LunaFacesCreateRequest(
    @JsonAlias("external_id") val externalId: String? = "",
    @JsonAlias("user_data") val userData: String? = "",
    @JsonAlias("avatar") val avatar: String? = "",
    @JsonAlias("lists") val lists: List<String>? = emptyList(),
    @JsonAlias("event_id") val eventId: String? = null,
    @JsonAlias("attribute") val attribute: LunaFacesCreateAttributeRequest?
) {
    data class LunaFacesCreateAttributeRequest(
        @JsonAlias("attribute_id") val attributeId: String? = "",
    )
}

data class LunaFaceCreateResponse(
    @JsonAlias("face_id") val faceId: String? = null,
    @JsonAlias("url") val url: String? = null,
    @JsonAlias("external_url") val externalUrl: String? = null,
)

data class LunaFacesUpdateRequest(
    @JsonAlias("external_id") val externalId: String? = "",
    @JsonAlias("event_id") val eventId: String? = null,
    @JsonAlias("user_data") val userData: String? = "",
    @JsonAlias("avatar") val avatar: String? = "",
)

data class LunaFacesResponse(
    @JsonAlias("faces") val faces: List<LunaFaceResponse>? = emptyList(),
)
data class LunaFaceResponse(
    @JsonAlias("face_id") val faceId: String? = null,
    @JsonAlias("account_id") val accountId: String? = null,
    @JsonAlias("event_id") val eventId: String? = null,
    @JsonAlias("user_data") val userData: String? = null,
    @JsonAlias("create_time") val createTime: String? = null,
    @JsonAlias("external_id") val externalId: String? = null,
    @JsonAlias("avatar") val avatar: String? = null,
    @JsonAlias("lists") val lists: List<String>? = emptyList()
)