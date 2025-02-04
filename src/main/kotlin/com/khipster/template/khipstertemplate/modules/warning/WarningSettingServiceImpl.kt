package com.khipster.template.khipstertemplate.modules.warning

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class WarningSettingServiceImpl(
    private val warningSettingRepo: WarningSettingRepo
) : WarningSettingService {

    override fun createWarningSetting(createWarningSetting: CreateWarningSetting): WarningSettingDTO {
        return warningSettingRepo.save(
            WarningSetting(
                warningName = createWarningSetting.warningName,
                status = createWarningSetting.status,
                gender = createWarningSetting.gender,
                mask = createWarningSetting.mask,
                listId = createWarningSetting.listId,
                rangeAge = createWarningSetting.rangeAge,
                warningCameras = createWarningSetting.warningCameras,
            ).apply {
                this.warningTimeEntities.addAll(
                    createWarningSetting.warningTimesDto.orEmpty().map { warningTimeDto ->
                        WarningTime(
                            startTime = warningTimeDto.startTime,
                            endTime = warningTimeDto.endTime,
                            dayOfWeek = warningTimeDto.dayOfWeek,
                        )
                    }
                )
            }
        ).toDto()

    }

    override fun updateWarningSetting(updateWarningSetting: UpdateWarningSetting): WarningSettingDTO {
        val warningSetting = warningSettingRepo.findById(updateWarningSetting.id!!).orElseThrow {
            throw RuntimeException("WarningSetting not found")
        }
        return warningSettingRepo.save(
            warningSetting.apply {
                warningName = updateWarningSetting.warningName
                status = updateWarningSetting.status
                gender = updateWarningSetting.gender
                mask = updateWarningSetting.mask
                listId = updateWarningSetting.listId
                rangeAge = updateWarningSetting.rangeAge
                warningCameras = updateWarningSetting.warningCameras
                warningTimeEntities.clear()
                warningTimeEntities.addAll(
                    updateWarningSetting.warningTimeEntities.orEmpty().map { warningTimeDto ->
                        WarningTime(
                            startTime = warningTimeDto.startTime,
                            endTime = warningTimeDto.endTime,
                            dayOfWeek = warningTimeDto.dayOfWeek,
                        )
                    }
                )
            }
        ).toDto()
    }
}
