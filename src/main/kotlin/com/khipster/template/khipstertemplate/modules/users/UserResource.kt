package com.khipster.template.khipstertemplate.modules.users

import com.khipster.template.khipstertemplate.config.requireIdEqualNotNull
import com.khipster.template.khipstertemplate.config.requireIdNotNull
import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import com.khipster.template.khipstertemplate.errors.BadRequestAlertException
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import tech.jhipster.web.util.HeaderUtil
import tech.jhipster.web.util.PaginationUtil

@RestController
@RequestMapping("/api")
class UserResource(
    private val userService: UserService,
    private val userQueryService: UserQueryService,
    private val userRepo: UserRepo
) {

    companion object {
        const val ENTITY_NAME = "user"
    }

    private var applicationName: String? = "test app name"

    @PostMapping("/user")
    fun createUser(@Valid @RequestBody createUserDTO: CreateUserDTO): ResponseEntity<ApiResponse<UserDTO>> {
        val result = userService.save(createUserDTO)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @PutMapping("/user/{id}")
    fun updateUser(
        @PathVariable(value = "id", required = false) id: Long,
        @Valid @RequestBody updateUserDTO: UpdateUserDTO
    ): ResponseEntity<ApiResponse<UserDTO>> {
        requireIdEqualNotNull(updateUserDTO.id, id) { BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid") }

        requireIdNotNull(userRepo.existsById(id)) {
            BadRequestAlertException(
                "Entity not found",
                ENTITY_NAME,
                "idnotfound"
            )
        }

        val result = userService.update(updateUserDTO)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @PatchMapping("/user/{id}")
    fun partialUpdateUser(
        @PathVariable(value = "id", required = false) id: Long,
        @Valid @RequestBody updateUserDTO: UpdateUserDTO
    ): ResponseEntity<ApiResponse<UserDTO>> {
        requireIdEqualNotNull(updateUserDTO.id, id) { BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid") }

        requireIdNotNull(userRepo.existsById(id)) {
            BadRequestAlertException(
                "Entity not found",
                ENTITY_NAME,
                "idnotfound"
            )
        }

        val result = userService.partialUpdate(updateUserDTO)
        return result?.let {
            it.wrapOrNotFound(
                headers = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, it.id.toString())
            )
        } ?: throw BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid")
    }

    @GetMapping("/user/count")
    fun countUsers(criteria: UserCriteria): ResponseEntity<ApiResponse<Long>> {
        return userQueryService.countByCriteria(criteria).wrapOrNotFound()
    }

    @GetMapping("/user")
    fun getAllUsers(
        criteria: UserCriteria,
        @ParameterObject pageable: Pageable
    ): ResponseEntity<ApiResponse<List<UserDTO>>> {
        val page = userQueryService.findByCriteria(criteria, pageable)
        val headers =
            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page)

        return page.content.wrapOrNotFound(headers = headers)
    }

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<ApiResponse<UserDTO>> {
        val userDTO = userService.findOne(id)
        return userDTO.wrapOrNotFound()
    }
}