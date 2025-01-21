package com.khipster.template.khipstertemplate.modules.attendance.holiday

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface HolidayRepo : JpaRepository<Holiday, Long>, JpaSpecificationExecutor<Holiday> {
}