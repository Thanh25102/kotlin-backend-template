package com.khipster.template.khipstertemplate.modules.attendance.branch

fun Branch.toDto(): BranchDto = BranchDto(
    id = id,
    title = title,
    description = description,
    createdAt = createdAt

)