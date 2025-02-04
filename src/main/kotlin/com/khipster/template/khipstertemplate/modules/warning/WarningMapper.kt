package com.khipster.template.khipstertemplate.modules.warning

fun WarningSetting.toDto() = WarningSettingDTO(
    id = id,
    warningName = warningName,
    status = status,
    gender = gender,
    mask = mask,
    createdAt = createdAt,
    listId = listId,
    rangeAge = rangeAge,
    warningTimesDto = warningTimeEntities?.toDto(),
    warningCameras = warningCameras,
)

fun WarningTime.toDto() = WarningTimeDTO(
    id = id,
    startTime = startTime,
    endTime = endTime,
    dayOfWeek = dayOfWeek,
)

fun MutableSet<WarningTime>.toDto() = map { it.toDto() }.toMutableSet()