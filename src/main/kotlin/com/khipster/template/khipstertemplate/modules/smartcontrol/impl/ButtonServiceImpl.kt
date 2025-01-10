package com.khipster.template.khipstertemplate.modules.smartcontrol.impl

import com.khipster.template.khipstertemplate.modules.smartcontrol.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ButtonServiceImpl(private val buttonRepo: ButtonRepo) : ButtonService {

    @Transactional(readOnly = false)
    override fun save(createButton: ButtonDTO): ButtonDTO {
        return buttonRepo.save(
            createButton.toEntity()
        ).toDto()
    }

    @Transactional(readOnly = false)
    override fun update(button: ButtonDTO): ButtonDTO {
        requireNotNull { button.id }
        return buttonRepo.save(
            button.toEntity()
        ).toDto()
    }

    @Transactional(readOnly = false)
    override fun update(button: List<ButtonDTO>): List<ButtonDTO> {
        return buttonRepo.saveAll(
            button.map { it.toEntity() }
        ).map { it.toDto() }
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<ButtonDTO> {
        return buttonRepo.findAll().map { it.toDto() }
    }
    @Transactional(readOnly = true)
    override fun findBySmartControlId(smartControlId: Int): List<ButtonDTO> {
        return buttonRepo.findBySmartControlId(smartControlId).map { it.toDto() }
    }

}