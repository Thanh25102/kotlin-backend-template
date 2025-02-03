package com.khipster.template.khipstertemplate.modules.faces.stream

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class StreamServiceImpl(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) : StreamService {
    override fun createStream(stream: LunaStreamCreateRequest): LunaStreamCreateResponse? {
        return restClient.post().uri("/lp5/6/luna-streams/1/streams")
            .body(stream)
            .retrieve()
            .body(LunaStreamCreateResponse::class.java)
    }

    override fun updateStream(id: String, stream: LunaStreamCreateRequest) {
        restClient.put().uri("/lp5/6/luna-streams/1/streams/$id")
            .body(stream)
            .retrieve()
    }

    override fun deleteStream(id: String) {
        restClient.delete()
            .uri("/lp5/6/luna-streams/1/streams/$id")
            .retrieve()
    }
}