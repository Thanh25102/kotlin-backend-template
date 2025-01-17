package com.khipster.template.khipstertemplate.modules.smartcontrol

interface SmartControlService {
    fun save(smartControlDTO: CreateSmartControlDTO): SmartControlDTO
    fun update(smartControlDTO: UpdateSmartControlDTO): SmartControlDTO
    fun findById(id: Int): SmartControlDTO
    fun findAllReference(): List<SmartControlDTO>
}