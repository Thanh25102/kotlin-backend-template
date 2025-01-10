package com.khipster.template.khipstertemplate.modules.smartcontrol

interface ButtonService {
    fun save(createButton: ButtonDTO): ButtonDTO
    fun update(button: ButtonDTO): ButtonDTO
    fun update(button: List<ButtonDTO>): List<ButtonDTO>
    fun findAll(): List<ButtonDTO>
    fun findBySmartControlId(smartControlId: Int): List<ButtonDTO>
}
