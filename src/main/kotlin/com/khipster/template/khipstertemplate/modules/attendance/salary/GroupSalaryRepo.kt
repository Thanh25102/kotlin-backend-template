package com.khipster.template.khipstertemplate.modules.attendance.salary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface GroupSalaryRepo : JpaRepository<GroupSalary, Long>, JpaSpecificationExecutor<GroupSalary> {
}