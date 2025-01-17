package com.khipster.template.khipstertemplate.modules.users

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {
    fun save(userDTO: CreateUserDTO): UserDTO
    fun update(userDTO: UpdateUserDTO): UserDTO
    fun partialUpdate(userDTO: UpdateUserDTO): UserDTO?
    fun findAll(pageable: Pageable): Page<UserDTO>
    fun findOne(id: Long): UserDTO?
    fun delete(id: Long)
}
