package com.khipster.template.khipstertemplate.modules.attendance.streamConfig

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StreamConfigServiceImpl(
    private val streamConfigRepo: StreamConfigRepo
) : StreamConfigService {
    override fun createStreamConfig(streamConfigCreate: StreamConfigCreate): StreamConfigDTO {
        val streamConfig = StreamConfig(
            streamId = streamConfigCreate.streamId,
            timeSheetType = streamConfigCreate.timeSheetType
        )
        return streamConfigRepo.save(streamConfig).toDto()
    }

    override fun updateStreamConfig(streamConfigUpdate: StreamConfigUpdate): StreamConfigDTO {
        val streamConfig =
            streamConfigRepo.findByIdOrNull(streamConfigUpdate.id) ?: throw RuntimeException("StreamConfig not found")
        return streamConfigRepo.save(
            streamConfig.copy(
                streamId = streamConfigUpdate.streamId,
                timeSheetType = streamConfigUpdate.timeSheetType
            )
        ).toDto()
    }

    override fun findAllStreamConfigs(): List<StreamConfigDTO> {
        return streamConfigRepo.findAll().map { it.toDto() }
    }
}