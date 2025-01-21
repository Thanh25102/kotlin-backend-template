package com.khipster.template.khipstertemplate.modules.attendance.operational

interface OperationalService {
    fun create(operationalHour: CreateOperationalDTO): OperationalDTO
    fun update(operationalHour: UpdateOperationalDTO): OperationalDTO
    fun delete(id: Int)
}