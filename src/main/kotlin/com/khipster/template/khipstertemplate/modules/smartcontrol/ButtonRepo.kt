package com.khipster.template.khipstertemplate.modules.smartcontrol

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ButtonRepo : JpaRepository<Buttons, Long>, JpaSpecificationExecutor<Buttons> {

    @Query("select b from Buttons b where b.smartControl.id = ?1")
    fun findBySmartControlId(id: Int): List<Buttons>

}
