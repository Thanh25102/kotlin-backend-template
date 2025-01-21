package com.khipster.template.khipstertemplate.modules.attendance.branch

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class BranchServiceImpl(
    private val branchRepo: BranchRepo,
): BranchService {
    override fun save(createBranchDto: CreateBranchDto): BranchDto {
        val branch = Branch(
            title = createBranchDto.title,
            description = createBranchDto.description,
        )
        return branchRepo.save(branch).toDto()
    }

    override fun update(updateBranchDto: UpdateBranchDto): BranchDto {
        val branch = branchRepo.findByIdOrNull(updateBranchDto.id)
        requireNotNull(branch) { "Branch not found" }

        return branchRepo.save(branch.also {
            it.title = updateBranchDto.title
            it.description = updateBranchDto.description

        }).toDto()
    }

    override fun partialUpdate(updateBranchDto: UpdateBranchDto): BranchDto? {
        val branch = branchRepo.findByIdOrNull(updateBranchDto.id)
        requireNotNull(branch) { "Branch not found" }

        return branchRepo.save(branch.also {
            updateBranchDto.title?.let { title -> it.title = title }
            updateBranchDto.description?.let { description -> it.description = description }
        }).toDto()
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}