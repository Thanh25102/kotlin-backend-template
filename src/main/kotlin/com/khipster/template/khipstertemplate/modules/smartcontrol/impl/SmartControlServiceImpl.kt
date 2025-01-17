package com.khipster.template.khipstertemplate.modules.smartcontrol.impl

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.khipster.template.khipstertemplate.config.get
import com.khipster.template.khipstertemplate.modules.smartcontrol.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
@Transactional
class SmartControlServiceImpl(
    private val smartControlRepo: SmartControlRepo,
    private val buttonRepo: ButtonRepo,
    private val restTemplate: RestTemplate
) : SmartControlService {

    override fun save(smartControlDTO: CreateSmartControlDTO): SmartControlDTO {
        val smartControlEntity = smartControlDTO.toEntity()

        val smartControlSaved = smartControlRepo.save(smartControlEntity).toDTO()

        val xmlMapper = XmlMapper()
        val response =
            restTemplate.get<String>(
                uri = "${smartControlSaved.domain}/status.xml",
                onError = { throw RuntimeException("Cannot get data from smart control") },
                onSuccess = { println("Success") }
            )
        val buttons = xmlMapper.readValue(response, Response::class.java).leds.entries.map { (key, value) ->
            ButtonDTO(
                name = key,
                value = value,
                smartControlId = smartControlSaved.id
            )
        }

        val btnSaved = buttonRepo.saveAll(buttons.map { it.toEntity() }).map { it.toDto() }
        return smartControlSaved.copy(buttons = btnSaved)
    }

    override fun update(smartControlDTO: UpdateSmartControlDTO): SmartControlDTO {
        val smartControlEntity = smartControlDTO.toEntity()
        val smartControlSaved = smartControlRepo.save(smartControlEntity).toDTO()
        return smartControlSaved
    }

    override fun findById(id: Int): SmartControlDTO {
        return smartControlRepo.findById(id).orElseThrow { RuntimeException("SmartControl not found") }.toDTO()
    }

    override fun findAllReference(): List<SmartControlDTO> {
        return smartControlRepo.findWithButton().map { it.toDTO() }
    }


}

@JacksonXmlRootElement(localName = "response")
data class Response(
    val leds: MutableMap<String, Int> = mutableMapOf()
) {
    @JsonAnySetter
    fun addLed(key: String, value: Int) {
        leds[key] = value
    }
}

