package com.khipster.template.khipstertemplate.modules.attendance.holiday

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class HolidayServiceImpl(
    private val holidayRepo: HolidayRepo,
) : HolidayService {

    override fun save(createHolidayDTO: CreateHolidayDTO): HolidayDTO {
        val holiday = Holiday(
            title = createHolidayDTO.title,
            description = createHolidayDTO.description,
            startDate = createHolidayDTO.startDate,
            endDate = createHolidayDTO.endDate,
            coefficient = createHolidayDTO.coefficient,
        )
        return holidayRepo.save(holiday).toDto()
    }

    override fun update(updateHolidayDTO: UpdateHolidayDTO): HolidayDTO {
        val holiday = holidayRepo.findByIdOrNull(updateHolidayDTO.id)
        requireNotNull(holiday) { "Entity not found" }
        return holidayRepo.save(holiday.apply {
            this.title = updateHolidayDTO.title
            this.description = updateHolidayDTO.description
            this.startDate = updateHolidayDTO.startDate
            this.endDate = updateHolidayDTO.endDate
            this.coefficient = updateHolidayDTO.coefficient
        }).toDto()
    }

    override fun partialUpdate(updateHolidayDTO: UpdateHolidayDTO): HolidayDTO? {
        val holiday = holidayRepo.findByIdOrNull(updateHolidayDTO.id)
        requireNotNull(holiday) { "Entity not found" }

        return holidayRepo.save(holiday.apply {
            this.title = updateHolidayDTO.title ?: this.title
            this.description = updateHolidayDTO.description ?: this.description
            this.startDate = updateHolidayDTO.startDate ?: this.startDate
            this.endDate = updateHolidayDTO.endDate ?: this.endDate
            this.coefficient = updateHolidayDTO.coefficient ?: this.coefficient
        }).toDto()
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}