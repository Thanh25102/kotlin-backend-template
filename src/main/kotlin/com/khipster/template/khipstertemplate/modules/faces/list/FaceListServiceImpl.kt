package com.khipster.template.khipstertemplate.modules.faces.list

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class FaceListServiceImpl(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) : FaceListService {
    override fun create(face: LunaListCreateRequest): LunaListCreateResponse? {
        return restClient.post().uri("/lp5/6/lists").body(face).retrieve().body(LunaListCreateResponse::class.java)
    }

    override fun update(face: LunaListUpdateRequest) {
        restClient.put().uri("/lp5/6/lists").body(face).retrieve()
    }
}