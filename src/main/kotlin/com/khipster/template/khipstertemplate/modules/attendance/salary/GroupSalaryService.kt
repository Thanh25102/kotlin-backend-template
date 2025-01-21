package com.khipster.template.khipstertemplate.modules.attendance.salary

interface GroupSalaryService {
    fun create(createGroupSalaryDTO: CreateGroupSalaryDTO): GroupSalaryDTO
    fun update(updateGroupSalaryDTO: UpdateGroupSalaryDTO): GroupSalaryDTO
    fun partialUpdate(updateGroupSalaryDTO: UpdateGroupSalaryDTO): GroupSalaryDTO?
    fun delete(id: Long)

}