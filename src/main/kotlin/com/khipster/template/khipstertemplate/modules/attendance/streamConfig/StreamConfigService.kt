package com.khipster.template.khipstertemplate.modules.attendance.streamConfig

interface StreamConfigService {

    fun createStreamConfig(streamConfigCreate: StreamConfigCreate): StreamConfigDTO

    fun updateStreamConfig(streamConfigUpdate: StreamConfigUpdate): StreamConfigDTO

    fun findAllStreamConfigs(): List<StreamConfigDTO>

}