package com.khipster.template.khipstertemplate.modules.faces.users

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.multipart.MultipartFile

@Service
class FaceUserServiceImpl(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) : FaceUserService {
    override fun detectFace(image: MultipartFile): LunaFaceResponse? {
        val detector = restClient.post().uri("/lp5/6/detector")
            .body(image)
            .retrieve()
            .body(LunaImagesResponse::class.java)

        val sdkDescriptor = restClient.post().uri("/lp5/6/sdk")
            .body(image)
            .retrieve()
            .body(LunaImagesEstimationsResponse::class.java)

        println("sdkDescriptor: $sdkDescriptor")
        println("detector: $detector")

        return null
    }

    override fun createFace(face: LunaFacesCreateRequest): LunaFaceCreateResponse? {
        val matcherFace = restClient.post().uri("/lp5/6/matcher/faces")
        val extractor = restClient.post().uri("/lp5/6/extractor")
        val createFace = restClient.post().uri("/lp5/6/faces")
        // detector binary file @return sampleId, url and url
        // sdk binary file : sdk descriptor`
        // matcher sdk descriptor : result kq trung` khop
        // extractor : sample id -> xac dinh attribute
        // face : attribute, avatar url, userdata , external id,
        return restClient.post().uri("/lp5/6/faces")
            .body(face)
            .retrieve()
            .body(LunaFaceCreateResponse::class.java)
    }

    override fun updateFace(face: LunaFacesUpdateRequest) {
        restClient.patch().uri("/lp5/6/faces").body(face)
    }
}