package com.khipster.template.khipstertemplate.modules.smartcontrol

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface SmartControlRepo : JpaRepository<SmartControls, Int>, JpaSpecificationExecutor<SmartControls> {

    @Query("SELECT sc FROM SmartControls sc")
    @EntityGraph(attributePaths = ["buttons"])
    fun findWithButton(): List<SmartControls>
}