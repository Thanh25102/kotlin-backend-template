package com.khipster.template.khipstertemplate.modules.attendance.operational

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface OperationalRepo : JpaRepository<OperationalHour, Long>, JpaSpecificationExecutor<OperationalHour> {
}