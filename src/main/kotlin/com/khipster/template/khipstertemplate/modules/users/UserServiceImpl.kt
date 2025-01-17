package com.khipster.template.khipstertemplate.modules.users

import com.khipster.template.khipstertemplate.domain.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserServiceImpl(private val userRepo: UserRepo) : UserService {

    @Transactional(readOnly = false)
    override fun save(userDTO: CreateUserDTO): UserDTO {
        return userRepo.save(
            User(
                login = userDTO.login,
                password = userDTO.password,
                firstName = userDTO.firstName,
                lastName = userDTO.lastName,
                email = userDTO.email,
                activated = userDTO.activated,
                langKey = userDTO.langKey!!,
                imageUrl = userDTO.imageUrl,
            )
        ).toDto()
    }

    @Transactional(readOnly = false)
    override fun update(userDTO: UpdateUserDTO): UserDTO {
        requireNotNull(userRepo.findByIdOrNull(userDTO.id)) { "Entity not found" }
        return userRepo.save(userDTO.toEntity()).toDto()
    }

    @Transactional(readOnly = false)
    override fun partialUpdate(userDTO: UpdateUserDTO): UserDTO? {
        return userRepo.findByIdOrNull(userDTO.id)?.let { existingUser ->
            val updatedUser = existingUser.apply {
                userDTO.firstName?.let { this.firstName = it }
                userDTO.lastName?.let { this.lastName = it }
                userDTO.email?.let { this.email = it }
                userDTO.activated?.let { this.activated = it }
                userDTO.langKey?.let { this.langKey = it }
                userDTO.imageUrl?.let { this.imageUrl = it }
            }
            userRepo.save(updatedUser).toDto()
        }
    }

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<UserDTO> {
        return userRepo.findAll(pageable).map { it.toDto() }
    }

    @Transactional(readOnly = true)
    override fun findOne(id: Long): UserDTO? {
        return userRepo.findByIdOrNull(id)?.toDto()
    }

    @Transactional(readOnly = false)
    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }

}