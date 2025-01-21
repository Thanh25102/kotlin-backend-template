package com.khipster.template.khipstertemplate.modules.attendance.salary

import java.math.BigDecimal
import java.time.Instant

data class CreateGroupSalaryDTO(
    var title: String? = null,
    var description: String? = null,
    var salary: BigDecimal? = null,
)

data class UpdateGroupSalaryDTO(
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var salary: BigDecimal? = null,
)

data class GroupSalaryDTO(
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var salary: BigDecimal? = null,
    var createdAt: Instant? = null,
)