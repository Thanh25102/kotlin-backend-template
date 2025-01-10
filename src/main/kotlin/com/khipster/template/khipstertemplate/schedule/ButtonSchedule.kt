package com.khipster.template.khipstertemplate.schedule

import com.khipster.template.khipstertemplate.modules.smartcontrol.SmartControlHandler
import com.khipster.template.khipstertemplate.modules.smartcontrol.SmartControlService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ButtonSchedule(private val asyncTask: AsyncTask) {

    @Scheduled(fixedRate = 1000)
    fun updateButtonValue() {
        asyncTask.asyncTask()
    }

}

@Service
class AsyncTask(
    private val smartControlService: SmartControlService,
) {
    @Async
    fun asyncTask() {
        val smartControls = smartControlService.findAllReference()
        val buttonsActive = smartControls.flatMap {
            it.buttons?.filter { it.value == 1 } ?: emptyList()
        }
//        smartControlHandler.sendPublicMessage("Active buttons: ${buttonsActive.size}")
    }
}