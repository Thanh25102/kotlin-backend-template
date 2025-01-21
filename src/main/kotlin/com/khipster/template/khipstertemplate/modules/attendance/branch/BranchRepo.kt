package com.khipster.template.khipstertemplate.modules.attendance.branch

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface BranchRepo : JpaRepository<Branch, Long>, JpaSpecificationExecutor<Branch> {
}

interface BranchGroupRepo : JpaRepository<BranchGroup, Long>, JpaSpecificationExecutor<BranchGroup> {
}