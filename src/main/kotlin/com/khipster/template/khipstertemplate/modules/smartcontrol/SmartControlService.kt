package com.khipster.template.khipstertemplate.modules.smartcontrol

interface SmartControlService {
    fun save(smartControlDTO: SmartControlDTO): SmartControlDTO
    fun findById(id: Int): SmartControlDTO
    fun findAllReference(): List<SmartControlDTO>
}