package com.khipster.template.khipstertemplate.modules.attendance.operational

import com.khipster.template.khipstertemplate.config.toInstant
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class OperationalServiceImpl(private val operationalRepo: OperationalRepo) : OperationalService {

    override fun create(operationalHour: CreateOperationalDTO): OperationalDTO {
        return operationalRepo.save(
            OperationalHour(
                title = operationalHour.title,
                startTime = operationalHour.startTime?.toInstant(),
                endTime = operationalHour.endTime?.toInstant(),
                dayOfWeek = operationalHour.dayOfWeek,
                coefficient = operationalHour.coefficient,
                coefficientOt = operationalHour.coefficientOt,
                isOvernight = operationalHour.isOvernight,
                startBreakTime = operationalHour.startBreakTime?.toInstant(),
                endBreakTime = operationalHour.endBreakTime?.toInstant(),
            )
        ).toDto()
    }

    override fun update(operationalHour: UpdateOperationalDTO): OperationalDTO {
        val operational = operationalRepo.findByIdOrNull(operationalHour.id)
        requireNotNull(operational) { "Operational not found" }
        return operationalRepo.save(
            operational.apply {
                title = operationalHour.title ?: title
                startTime = operationalHour.startTime?.toInstant() ?: startTime
                endTime = operationalHour.endTime?.toInstant() ?: endTime
                dayOfWeek = operationalHour.dayOfWeek ?: dayOfWeek
                coefficient = operationalHour.coefficient ?: coefficient
                coefficientOt = operationalHour.coefficientOt ?: coefficientOt
                isOvernight = operationalHour.isOvernight ?: isOvernight
                startBreakTime = operationalHour.startBreakTime?.toInstant() ?: startBreakTime
                endBreakTime = operationalHour.endBreakTime?.toInstant() ?: endBreakTime
            }
        ).toDto()
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}