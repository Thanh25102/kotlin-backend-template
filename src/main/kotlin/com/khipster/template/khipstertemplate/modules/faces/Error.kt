package com.khipster.template.khipstertemplate.modules.faces

data class ErrorResponse(
    val errorCode: Int,
    val desc: String,
    val detail: String,
    val link: String
)