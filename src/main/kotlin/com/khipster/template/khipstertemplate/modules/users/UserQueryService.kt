package com.khipster.template.khipstertemplate.modules.users

import com.khipster.template.khipstertemplate.domain.User
import com.khipster.template.khipstertemplate.domain.User_
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.service.QueryService

@Service
@Transactional(readOnly = true)
class UserQueryService(
    private val userRepo: UserRepo,
) : QueryService<User>() {

    @Transactional(readOnly = true)
    fun findByCriteria(criteria: UserCriteria?): MutableList<User> {
        val specification = createSpecification(criteria)
        return userRepo.findAll(specification)
    }

    @Transactional(readOnly = true)
    fun findByCriteria(criteria: UserCriteria?, page: Pageable): Page<UserDTO> {
        val specification = createSpecification(criteria)
        return userRepo.findAll(specification, page).map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun countByCriteria(criteria: UserCriteria?): Long {
        val specification = createSpecification(criteria)
        return userRepo.count(specification)
    }

    private fun createSpecification(criteria: UserCriteria?): Specification<User?> {
        var specification: Specification<User?> = Specification.where(null)

        criteria?.let {
            val (id, login, email, activated) = it
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }
            id?.let {
                specification = specification.and(buildRangeSpecification(id, User_.id))
            }

            login?.let {
                specification = specification.and(buildStringSpecification(login, User_.login))
            }

            email?.let {
                specification = specification.and(buildStringSpecification(email, User_.email))
            }

            activated?.let {
                specification = specification.and(buildSpecification(activated, User_.activated))
            }
        }

        return specification
    }

}