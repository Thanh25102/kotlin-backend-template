package com.khipster.template.khipstertemplate.modules.smartcontrol

fun SmartControlDTO.toEntity(): SmartControls {
    return SmartControls(
        id = this.id,
        name = this.name,
        domain = this.domain,
    )
}

fun CreateSmartControlDTO.toEntity(): SmartControls {
    return SmartControls(
        name = this.name,
        domain = this.domain,
    )
}

fun UpdateSmartControlDTO.toEntity(): SmartControls {
    return SmartControls(
        id = this.id,
        name = this.name,
        domain = this.domain,
    )
}


fun SmartControls.toDTO(): SmartControlDTO {
    return SmartControlDTO(
        id = this.id,
        name = this.name,
        domain = this.domain,
        buttons = this.buttons.map { it.toDto() }
    )
}