package com.khipster.template.khipstertemplate.modules.warning

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface WarningSettingRepo : JpaRepository<WarningSetting, Long>, JpaSpecificationExecutor<WarningSetting>