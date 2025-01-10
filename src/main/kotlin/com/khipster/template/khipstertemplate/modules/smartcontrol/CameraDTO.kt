package com.khipster.template.khipstertemplate.modules.smartcontrol

data class CameraDTO(val id: String, val name: String, val cameraInfoId: String, val num: Int, val cameraInfo: CameraInfo)
data class CameraInfo(val broadcastUrl: String)
data class IntegrationData(
    val name:String,
    val cameras: List<CameraDTO>,
    val id: String,
)
data class IntegrationError(
    val message: String,
    val code: String,
)
data class IntegrationResponse(
    val data: IntegrationData,
    val error: IntegrationError,
)