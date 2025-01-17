package com.khipster.template.khipstertemplate.modules.smartcontrol

data class SmartControlDTO(
    val id: Int? = null,
    val name: String? = null,
    val domain: String? = null,
    val buttons: List<ButtonDTO>? = emptyList()
)

data class CreateSmartControlDTO(
    val name: String? = null,
    val domain: String? = null,
)

data class UpdateSmartControlDTO(
    var id: Int? = null,
    val name: String? = null,
    val domain: String? = null,
)