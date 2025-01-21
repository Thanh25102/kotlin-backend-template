package com.khipster.template.khipstertemplate.modules.attendance.salary

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GroupSalaryServiceImpl(
    private val groupSalaryRepo: GroupSalaryRepo,
) : GroupSalaryService {

    override fun create(createGroupSalaryDTO: CreateGroupSalaryDTO): GroupSalaryDTO {
        return groupSalaryRepo.save(
            GroupSalary(
                title = createGroupSalaryDTO.title,
                description = createGroupSalaryDTO.description,
                salary = createGroupSalaryDTO.salary,
            )
        ).toDto()
    }

    override fun update(updateGroupSalaryDTO: UpdateGroupSalaryDTO): GroupSalaryDTO {
        return groupSalaryRepo.save(
            GroupSalary(
                id = updateGroupSalaryDTO.id,
                title = updateGroupSalaryDTO.title,
                description = updateGroupSalaryDTO.description,
                salary = updateGroupSalaryDTO.salary,
            )
        ).toDto()
    }

    override fun partialUpdate(updateGroupSalaryDTO: UpdateGroupSalaryDTO): GroupSalaryDTO? {
        return groupSalaryRepo.findByIdOrNull(updateGroupSalaryDTO.id)?.apply {
            updateGroupSalaryDTO.title?.let { title = it }
            updateGroupSalaryDTO.description?.let { description = it }
            updateGroupSalaryDTO.salary?.let { salary = it }
        }?.toDto()
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}