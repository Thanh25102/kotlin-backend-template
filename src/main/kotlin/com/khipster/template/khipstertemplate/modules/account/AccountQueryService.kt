package com.khipster.template.khipstertemplate.modules.account

import com.khipster.template.khipstertemplate.domain.User
import com.khipster.template.khipstertemplate.domain.User_
import com.khipster.template.khipstertemplate.repository.UserRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.service.QueryService

@Service
@Transactional(readOnly = true)
class AccountQueryService(
    private val accountRepo: UserRepo
) : QueryService<User>() {

    fun findByCriteria(criteria: AccountCriteria?): List<AccountDTO> {
        val specification = createSpecification(criteria)
        return accountRepo.findAll(specification)
            .map { it.toDto() }
    }

    fun findByCriteria(criteria: AccountCriteria?, page: Pageable): Page<AccountDTO> {
        val specification = createSpecification(criteria)
        return accountRepo.findAll(specification, page)
            .map { it.toDto() }
    }

    fun countByCriteria(criteria: AccountCriteria?): Long {
        val specification = createSpecification(criteria)
        return accountRepo.count(specification)
    }


    fun createSpecification(criteria: AccountCriteria?): Specification<User?> {
        var specification: Specification<User?> = Specification.where(null)

        criteria?.let {
            val (id, login, firstName, lastName, email, activated) = it
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }

            id?.let {
                specification = specification.and(buildSpecification(id, User_.id))
            }

            login?.let {
                specification = specification.and(buildStringSpecification(login, User_.login))
            }

            firstName?.let {
                specification = specification.and(buildStringSpecification(firstName, User_.firstName))
            }

            lastName?.let {
                specification = specification.and(buildStringSpecification(lastName, User_.lastName))
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