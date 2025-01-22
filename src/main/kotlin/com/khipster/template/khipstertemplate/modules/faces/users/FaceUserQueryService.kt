package com.khipster.template.khipstertemplate.modules.faces.users

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class FaceUserQueryService(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) {

}