package com.khipster.template.khipstertemplate.modules.attendance.salary

import java.math.BigDecimal
import java.time.Instant

data class CreateGroupSalaryDTO(
    var title: String? = null, // chua co validate title not null
    var description: String? = null,
    var salary: BigDecimal? = null, // chua co validate salary > 0
)

data class UpdateGroupSalaryDTO(
    var id: Long? = null,
    var title: String? = null, // chua co validate title not null
    var description: String? = null,
    var salary: BigDecimal? = null, // chua co validate salary > 0
)

data class GroupSalaryDTO(
    var id: Long? = null,
    var title: String? = null, // chua co validate title not null
    var description: String? = null,
    var salary: BigDecimal? = null, // chua co validate salary > 0
    var createdAt: Instant? = null,
)