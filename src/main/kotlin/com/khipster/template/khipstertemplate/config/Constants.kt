package com.khipster.template.khipstertemplate.config

// Regex for acceptable logins
const val LOGIN_REGEX: String = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$"
const val SYSTEM_ACCOUNT: String = "system"
const val DEFAULT_LANGUAGE: String = "en"

const val PASSWORD_MIN_LENGTH: Int = 4
const val PASSWORD_MAX_LENGTH: Int = 64