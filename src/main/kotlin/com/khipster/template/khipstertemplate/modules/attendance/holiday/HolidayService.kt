package com.khipster.template.khipstertemplate.modules.attendance.holiday

interface HolidayService {
    fun save(createHolidayDTO: CreateHolidayDTO): HolidayDTO
    fun update(updateHolidayDTO: UpdateHolidayDTO): HolidayDTO
    fun partialUpdate(updateHolidayDTO: UpdateHolidayDTO): HolidayDTO?
    fun delete(id: Long)
}