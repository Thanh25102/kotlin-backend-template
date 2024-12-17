package com.khipster.template.khipstertemplate.repository

import com.khipster.template.khipstertemplate.domain.Authority
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Spring Data JPA repository for the [Authority] entity.
 */

interface AuthorityRepository : JpaRepository<Authority, String>
