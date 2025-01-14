package com.khipster.template.khipstertemplate.modules.notifications


data class NotificationMessage(
    val recipient: String? = null,
    val title: String? = "",
    val body: String? = "",
    val image: String? = null,
    val data: Map<String, String>? = emptyMap()
)
