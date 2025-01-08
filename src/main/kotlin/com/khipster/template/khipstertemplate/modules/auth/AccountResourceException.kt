package com.khipster.template.khipstertemplate.modules.auth

class AccountResourceException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}