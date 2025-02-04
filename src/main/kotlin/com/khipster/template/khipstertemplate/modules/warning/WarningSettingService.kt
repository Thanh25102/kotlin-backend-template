package com.khipster.template.khipstertemplate.modules.warning

interface WarningSettingService {

    fun createWarningSetting(createWarningSetting: CreateWarningSetting): WarningSettingDTO

    fun updateWarningSetting(updateWarningSetting: UpdateWarningSetting): WarningSettingDTO

}