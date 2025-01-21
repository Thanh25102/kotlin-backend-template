package com.khipster.template.khipstertemplate.modules.attendance.salary

fun GroupSalary.toDto(): GroupSalaryDTO = GroupSalaryDTO(
    id = id,
    title = title,
    description = description,
    salary = salary,
    createdAt = createdAt,
)