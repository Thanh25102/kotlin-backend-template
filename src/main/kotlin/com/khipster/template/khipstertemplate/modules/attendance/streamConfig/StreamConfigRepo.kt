package com.khipster.template.khipstertemplate.modules.attendance.streamConfig

import org.springframework.data.jpa.repository.JpaRepository

interface StreamConfigRepo : JpaRepository<StreamConfig, Long> {
}