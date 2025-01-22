package com.khipster.template.khipstertemplate.modules.faces.users

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class FaceUserServiceImpl(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) : FaceUserService {

    override fun createFace(face: LunaFacesCreateRequest): LunaFaceCreateResponse? {
        return restClient.post().uri("/lp5/6/faces")
            .body(face)
            .retrieve()
            .body(LunaFaceCreateResponse::class.java)
    }

    override fun updateFace(face: LunaFacesUpdateRequest) {
        restClient.patch().uri("/lp5/6/faces").body(face)
    }
}