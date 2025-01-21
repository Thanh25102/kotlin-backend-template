package com.khipster.template.khipstertemplate.modules.account

import com.khipster.template.khipstertemplate.config.security.USER
import com.khipster.template.khipstertemplate.domain.User
import com.khipster.template.khipstertemplate.repository.AuthorityRepo
import com.khipster.template.khipstertemplate.repository.UserRepo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.security.RandomUtil

@Service
@Transactional
class AccountServiceImpl(
    private val accountRepo: UserRepo,
    private val passwordEncoder: PasswordEncoder,
    private val authorityRepo: AuthorityRepo
) : AccountService {
    override fun save(account: CreateAccountDto): AccountDTO {
        return accountRepo.save(
            User(
                login = account.login,
                password = passwordEncoder.encode(account.password),
                firstName = account.firstName,
                lastName = account.lastName,
                email = account.email,
                imageUrl = account.imageUrl,
                activated = false,
                activationKey = RandomUtil.generateActivationKey(),
                authorities = authorityRepo.findByIdOrNull(USER)?.let { mutableSetOf(it) } ?: mutableSetOf()
            )
        ).toDto()
    }

    override fun update(account: UpdateAccountDto): AccountDTO {
        val user = accountRepo.findByIdOrNull(account.id)
        requireNotNull(user) { "User not found" }
        return accountRepo.save(
            user.apply {
                login = account.login
                firstName = account.firstName
                lastName = account.lastName
                password = passwordEncoder.encode(account.password)
                email = account.email
            }
        ).toDto()

    }

    override fun partialUpdate(account: UpdateAccountDto): AccountDTO {
        val user = accountRepo.findByIdOrNull(account.id)
        requireNotNull(user) { "User not found" }
        return accountRepo.save(
            user.apply {
                login = account.login ?: login
                firstName = account.firstName ?: firstName
                lastName = account.lastName ?: lastName
                password = if (account.password != null) passwordEncoder.encode(account.password) else password
                email = account.email ?: email
            }
        ).toDto()
    }

    override fun deActivated(id: Long): AccountDTO {
        TODO("Not yet implemented")
    }
}