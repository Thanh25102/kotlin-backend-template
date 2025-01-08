package com.khipster.template.khipstertemplate.modules.auth

import java.lang.RuntimeException

class UsernameAlreadyUsedException : RuntimeException("Login name or email already used!") {
}