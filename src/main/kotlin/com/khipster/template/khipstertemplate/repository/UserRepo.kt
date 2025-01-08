package com.khipster.template.khipstertemplate.repository

import com.khipster.template.khipstertemplate.domain.User
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.*

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
interface UserRepo : JpaRepository<User, Long> {

    fun findOneByActivationKey(activationKey: String): User?

    fun findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(dateTime: Instant): List<User>

    fun findOneByResetKey(resetKey: String): User?

    fun findOneByEmailIgnoreCase(email: String?): User?

    fun findOneByLoginOrEmail(login: String, email: String? = null): User?

    @EntityGraph(attributePaths = ["authorities"])
    @Cacheable(cacheNames = [USERS_BY_LOGIN_CACHE])
    fun findOneWithAuthoritiesByLogin(login: String): Optional<User>

    @EntityGraph(attributePaths = ["authorities"])
    @Cacheable(cacheNames = [USERS_BY_EMAIL_CACHE])
    fun findOneWithAuthoritiesByEmailIgnoreCase(email: String): Optional<User>

    fun findAllByIdNotNullAndActivatedIsTrue(pageable: Pageable): Page<User>

    companion object {

        const val USERS_BY_LOGIN_CACHE: String = "usersByLogin"

        const val USERS_BY_EMAIL_CACHE: String = "usersByEmail"
    }
}
