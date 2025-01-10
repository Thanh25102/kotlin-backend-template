package com.khipster.template.khipstertemplate.modules.smartcontrol


fun Buttons.toDto(): ButtonDTO = ButtonDTO(
    id = id,
    name = name,
    value = value,
    cameraId = cameraId,
    smartControlId = smartControl?.id
)

fun ButtonDTO.toEntity(): Buttons = Buttons(
    id = id,
    name = name,
    value = value,
    cameraId = cameraId,
    smartControl = smartControlId?.let { SmartControls(id = it) }
)