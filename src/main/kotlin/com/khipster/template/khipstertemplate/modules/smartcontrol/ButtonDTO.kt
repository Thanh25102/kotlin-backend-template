package com.khipster.template.khipstertemplate.modules.smartcontrol

data class ButtonDTO(
    var id: Int? = null,
    var name: String? = null,
    var value: Int = 0,
    var cameraId: String? = null,
    var smartControlId: Int? = null
)