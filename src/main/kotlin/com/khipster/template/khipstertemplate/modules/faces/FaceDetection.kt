package com.khipster.template.khipstertemplate.modules.faces

data class Face(
    val faceId: String,
    val accountId: String,
    val eventId: String,
    val userData: String,
    val createTime: String,
    val externalId: String,
    val avatar: String,
    val lists: List<String>
)

data class FaceResponse(
    val faces: List<Face>
)
