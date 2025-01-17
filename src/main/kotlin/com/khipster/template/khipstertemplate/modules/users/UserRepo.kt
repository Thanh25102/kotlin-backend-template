package com.khipster.template.khipstertemplate.modules.users

import com.khipster.template.khipstertemplate.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor


interface UserRepo: JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}