package com.khipster.template.khipstertemplate.modules.faces.users

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestClient
import org.springframework.web.multipart.MultipartFile

@Service
class FaceUserServiceImpl(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) : FaceUserService {
    override fun detectFace(image: MultipartFile): DetectionResponse? {
        val body = LinkedMultiValueMap<String, Any>().apply {
            add("image", image.resource)
        }

        val detector = restClient.post().uri("/lp5/6/detector")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(body)
            .retrieve()
            .body(LunaImagesResponse::class.java)

        val sdkEstimateDescriptor =
            restClient.post().uri("/lp5/6/sdk?estimate_face_descriptor=1&detect_face=1&estimate_face_warp=1")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body)
                .retrieve()
                .body(LunaImagesEstimationsResponse::class.java)

        return DetectionResponse(detector, sdkEstimateDescriptor)
    }

    override fun createFace(face: FaceCreateRequest): FaceCreateResponse? {
        val lunaFaceCreateRq = face.toLunaMatterRequest()

        val customMapper = ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

        val matcherFace = restClient.post().uri("/lp5/6/matcher/faces")
            .contentType(MediaType.APPLICATION_JSON)
            .body(lunaFaceCreateRq)
            .retrieve()
            .body(Array<LunaMatcherFaceResponse>::class.java)
            ?.toList()

        val extractor = restClient.post().uri("/lp5/6/extractor?extract_basic_attributes=1")
            .contentType(MediaType.APPLICATION_JSON)
            .body(face.sampleIds ?: emptyList<String>())
            .retrieve()
            .body(Array<LunaExtractorResponse>::class.java)
            ?.toList()

        val faceCreateRequest = LunaFacesCreateRequest(
            externalId = face.externalId,
            userData = face.information,
            lists = face.lists,
            attribute = LunaFacesCreateRequest.LunaFacesCreateAttributeRequest(
                attributeId = extractor?.firstOrNull()?.attributeId
            ),
            avatar = extractor?.firstOrNull()?.url
        )

        val jsonFaceCreateRq = customMapper.writeValueAsString(faceCreateRequest)

        val createFace = restClient.post().uri("/lp5/6/faces")
            .contentType(MediaType.APPLICATION_JSON)
            .body(jsonFaceCreateRq)
            .retrieve()
            .body(LunaFaceCreateResponse::class.java)

        return FaceCreateResponse(matcherFace, extractor, createFace)
    }

    override fun updateFace(face: LunaFacesUpdateRequest) {
        restClient.patch().uri("/lp5/6/faces").body(face)
    }
}