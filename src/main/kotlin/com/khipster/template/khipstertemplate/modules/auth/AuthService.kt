package com.khipster.template.khipstertemplate.modules.auth

import com.khipster.template.khipstertemplate.config.security.USER
import com.khipster.template.khipstertemplate.domain.User
import com.khipster.template.khipstertemplate.repository.AuthorityRepo
import com.khipster.template.khipstertemplate.repository.AuthRepo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.security.RandomUtil

@Service
@Transactional
class AuthService(
    private val authRepo: AuthRepo,
    private val passwordEncoder: PasswordEncoder,
    private val authorityRepo: AuthorityRepo
) {
    fun register(userRegister: UserRegisterDTO): User {
        authRepo.findOneByLoginOrEmail(userRegister.login, userRegister.email)?.let {
            // check non-active user
            throw UsernameAlreadyUsedException()
        }

        val user = User(
            login = userRegister.login,
            password = passwordEncoder.encode(userRegister.password),
            firstName = userRegister.firstName,
            lastName = userRegister.lastName,
            email = userRegister.email?.lowercase(),
            imageUrl = userRegister.imageUrl,
            activated = false,
            activationKey = RandomUtil.generateActivationKey(),
            authorities = authorityRepo.findByIdOrNull(USER)?.let { mutableSetOf(it) } ?: mutableSetOf()
        )

        val userSaved = authRepo.save(user)
        // clear cache if exist

        return userSaved
    }

    fun activate(key: String): User? {
        return authRepo.findOneByActivationKey(key)?.let {
            it.activated = true
            it.activationKey = null
            authRepo.save(it)
        }
    }
}