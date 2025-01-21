package com.khipster.template.khipstertemplate.modules.attendance.branch

import java.time.Instant


data class BranchDto(
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var createdAt: Instant? = null,
    var branchGroupIds: List<String>? = null,
)

data class CreateBranchDto(
    var title: String? = null,
    var description: String? = null,
    var branchGroupIds: List<String>? = null
)

data class UpdateBranchDto(
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var branchGroupIds: List<String>? = null
)

