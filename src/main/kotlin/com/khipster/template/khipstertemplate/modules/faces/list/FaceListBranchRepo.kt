package com.khipster.template.khipstertemplate.modules.faces.list

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface FaceListBranchRepo : JpaRepository<FaceListBranch, Long> , JpaSpecificationExecutor<FaceListBranch> {

    @Query("from FaceListBranch f where f.branch.id = :branchId")
    fun findByBranchId(branchId: Long): MutableList<FaceListBranch>

}
