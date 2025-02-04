package com.khipster.template.khipstertemplate.modules.faces.list

import com.khipster.template.khipstertemplate.config.toJsonSnakeCase
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class FaceListServiceImpl(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) : FaceListService {
    override fun create(face: LunaListCreateRequest): LunaListCreateResponse? {
        return restClient.post().uri("/lp5/6/lists")
            .contentType(MediaType.APPLICATION_JSON)
            .body(face.toJsonSnakeCase())
            .retrieve()
            .body(LunaListCreateResponse::class.java)
    }

    override fun update(face: LunaListUpdateRequest) {
        restClient.put().uri("/lp5/6/lists")
            .contentType(MediaType.APPLICATION_JSON)
            .body(face.toJsonSnakeCase())
            .retrieve()
    }
}