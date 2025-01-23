package com.khipster.template.khipstertemplate.modules.faces.users

import com.fasterxml.jackson.annotation.JsonAlias
import com.khipster.template.khipstertemplate.modules.faces.ErrorResponse
import com.khipster.template.khipstertemplate.modules.faces.Rect
import com.khipster.template.khipstertemplate.modules.faces.Samples

data class LunaFacesCreateRequest(
    @JsonAlias("external_id") val externalId: String? = "",
    @JsonAlias("user_data") val userData: String? = "",
    @JsonAlias("avatar") val avatar: String? = "",
    @JsonAlias("lists") val lists: List<String>? = emptyList(),
    @JsonAlias("event_id") val eventId: String? = null,
    @JsonAlias("attribute") val attribute: LunaFacesCreateAttributeRequest?
) {
    data class LunaFacesCreateAttributeRequest(
        @JsonAlias("attribute_id") val attributeId: String? = "",
    )
}

data class LunaFaceCreateResponse(
    @JsonAlias("face_id") val faceId: String? = null,
    @JsonAlias("url") val url: String? = null,
    @JsonAlias("external_url") val externalUrl: String? = null,
)

data class LunaFacesUpdateRequest(
    @JsonAlias("external_id") val externalId: String? = "",
    @JsonAlias("event_id") val eventId: String? = null,
    @JsonAlias("user_data") val userData: String? = "",
    @JsonAlias("avatar") val avatar: String? = "",
)

data class LunaFacesResponse(
    @JsonAlias("faces") val faces: List<LunaFaceResponse>? = emptyList(),
)

data class LunaFaceResponse(
    @JsonAlias("face_id") val faceId: String? = null,
    @JsonAlias("account_id") val accountId: String? = null,
    @JsonAlias("event_id") val eventId: String? = null,
    @JsonAlias("user_data") val userData: String? = null,
    @JsonAlias("create_time") val createTime: String? = null,
    @JsonAlias("external_id") val externalId: String? = null,
    @JsonAlias("avatar") val avatar: String? = null,
    @JsonAlias("lists") val lists: List<String>? = emptyList()
)

data class LunaImagesResponse(
    @JsonAlias("images")
    val images: List<LunaImageResponse>? = emptyList()
) {
    data class LunaImageResponse(
        @JsonAlias("filename")
        val filename: String? = null,

        @JsonAlias("status")
        val status: Int? = null,

        @JsonAlias("error")
        val error: ErrorResponse? = null,

        @JsonAlias("description")
        val description: LunaDescriptionResponse? = null
    ) {
        data class LunaDescriptionResponse(
            @JsonAlias("samples")
            val samples: Samples? = null
        )
    }
}


data class LunaImagesEstimationsResponse(
    @JsonAlias("images_estimations")
    val imagesEstimations: List<LunaImagesEstimationResponse> = emptyList()
) {
    data class LunaImagesEstimationResponse(
        @JsonAlias("filename")
        val filename: String? = null,

        @JsonAlias("estimations")
        val estimations: List<LunaImageFacesResponse>? = emptyList(),

        @JsonAlias("image_estimations")
        val imageEstimations: Any? = null
    ) {
        data class LunaImageFacesResponse(
            @JsonAlias("face")
            val face: List<LunaFaceImageResponse>? = emptyList(),

            @JsonAlias("body")
            val body: Any? = null
        ) {
            data class LunaFaceImageResponse(
                @JsonAlias("detection")
                val detection: LunaDetectionResponse? = null
            ) {
                data class LunaDetectionResponse(
                    @JsonAlias("detection")
                    val detection: Rect? = null,

                    @JsonAlias("warp")
                    val warp: String? = null,

                    @JsonAlias("attributes")
                    val attributes: LunaAttributeResponse? = null
                ) {
                    data class LunaAttributeResponse(
                        @JsonAlias("descriptor")
                        val descriptor: LunaDescriptorResponse
                    ) {
                        data class LunaDescriptorResponse(
                            @JsonAlias("sdk_descriptor")
                            val sdkDescriptor: String? = null,

                            @JsonAlias("score")
                            val score: Double? = null
                        )
                    }
                }
            }
        }
    }
}

data class LunaMatcherFaceRequest(
    @JsonAlias("references")
    val references: List<Reference>? = emptyList(),
    @JsonAlias("candidates")
    val candidates: List<Candidate>? = emptyList()
) {
    data class Reference(
        @JsonAlias("data")
        val data: String? = null,
        @JsonAlias("type")
        val type: String? = null,
        @JsonAlias("id")
        val id: String? = null
    )

    data class Candidate(
        @JsonAlias("filters")
        val filters: Filters? = null,
        @JsonAlias("limit")
        val limit: Int? = null,
        @JsonAlias("threshold")
        val threshold: Double? = null
    ) {
        data class Filters(
            @JsonAlias("origin")
            val origin: String? = null,
            @JsonAlias("external_ids")
            val externalIds: List<String>? = emptyList(),
            @JsonAlias("list_id")
            val listId: String? = null
        )
    }
}

data class LunaMatcherFaceResponse(
    @JsonAlias("reference")
    val reference: Reference? = null,
    @JsonAlias("matches")
    val matches: List<Match>? = emptyList()
) {
    data class Reference(
        @JsonAlias("id")
        val id: String? = null,
        @JsonAlias("type")
        val type: String? = null
    )
    data class Match(
        @JsonAlias("result")
        val result: List<Result>? = emptyList(),
        @JsonAlias("filters")
        val filters: Filters? = null
    ) {
        data class Result(
            @JsonAlias("face")
            val face: Face? = null,
            @JsonAlias("similarity")
            val similarity: Double? = null
        ) {
            data class Face(
                @JsonAlias("lists")
                val lists: List<String>? = emptyList(),
                @JsonAlias("event_id")
                val eventId: String? = null,
                @JsonAlias("create_time")
                val createTime: String? = null,
                @JsonAlias("external_id")
                val externalId: String? = null,
                @JsonAlias("avatar")
                val avatar: String? = null,
                @JsonAlias("user_data")
                val userData: String? = null,
                @JsonAlias("account_id")
                val accountId: String? = null,
                @JsonAlias("face_id")
                val faceId: String? = null
            )
        }

        data class Filters(
            @JsonAlias("origin")
            val origin: String? = null,
            @JsonAlias("external_ids")
            val externalIds: List<String>? = emptyList(),
            @JsonAlias("list_id")
            val listId: String? = null
        )
    }
}

data class LunaExtractorResponse(
    @JsonAlias("attribute_id")
    val attributeId: String? = null,
    @JsonAlias("url")
    val url: String? = null,
    @JsonAlias("samples")
    val samples: List<String>? = emptyList(),
    @JsonAlias("score")
    val score: Double? = null,
    @JsonAlias("basic_attributes")
    val basicAttributes: BasicAttributes? = null,
    @JsonAlias("external_url")
    val externalUrl: String? = null
) {
    data class BasicAttributes(
        @JsonAlias("ethnicities")
        val ethnicities: Ethnicities? = null,
        @JsonAlias("age")
        val age: Int? = null,
        @JsonAlias("gender")
        val gender: Int? = null
    ) {
        data class Ethnicities(
            @JsonAlias("predominant_ethnicity")
            val predominantEthnicity: String? = null,
            @JsonAlias("estimations")
            val estimations: EthnicityEstimations? = null
        ) {
            data class EthnicityEstimations(
                @JsonAlias("asian")
                val asian: Double? = null,
                @JsonAlias("indian")
                val indian: Double? = null,
                @JsonAlias("caucasian")
                val caucasian: Double? = null,
                @JsonAlias("african_american")
                val africanAmerican: Double? = null
            )
        }
    }
}



