package com.khipster.template.khipstertemplate.modules.attendance.branch

interface BranchService {
    fun save(createBranchDto: CreateBranchDto): BranchDto
    fun update(updateBranchDto: UpdateBranchDto): BranchDto
    fun partialUpdate(updateBranchDto: UpdateBranchDto): BranchDto?
    fun delete(id: Long)
}