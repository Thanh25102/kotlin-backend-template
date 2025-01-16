package com.khipster.template.khipstertemplate.schedule

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.khipster.template.khipstertemplate.config.get
import com.khipster.template.khipstertemplate.modules.smartcontrol.ButtonDTO
import com.khipster.template.khipstertemplate.modules.smartcontrol.SmartControlService
import com.khipster.template.khipstertemplate.modules.smartcontrol.impl.Response
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ButtonSchedule(private val asyncTask: AsyncTask,private val messagingTemplate: SimpMessagingTemplate) {

    @Scheduled(fixedRate = 1000)
    fun updateButtonValue() {
        val buttons = asyncTask.asyncTask()
        messagingTemplate.convertAndSend("/topic/smart-control", buttons)
    }

}

@Service
class AsyncTask(
    private val smartControlService: SmartControlService,
    private val restTemplate: RestTemplate
) {
    @Async
    fun asyncTask(): List<ButtonDTO> {
        val xmlMapper = XmlMapper()

        val smartControls = smartControlService.findAllReference()
        return smartControls.map {
            val response = restTemplate.get<String>(
                uri = "${it.domain}/status.xml",
                onError = { throw RuntimeException("Cannot get data from smart control") },
                onSuccess = { println("Success") }
            )
            val buttons = it.buttons ?: emptyList()
             xmlMapper.readValue(response, Response::class.java).leds.entries.mapNotNull { (key, value) ->
                 buttons.find { it.name == key }?.apply {
                    value.let {
                        this.value = it
                    }
                }
            }.filter { it.value == 1 }
        }.flatten()
    }
}