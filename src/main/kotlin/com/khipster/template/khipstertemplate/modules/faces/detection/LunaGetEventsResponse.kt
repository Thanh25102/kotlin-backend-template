package com.khipster.template.khipstertemplate.modules.faces.detection

import com.fasterxml.jackson.annotation.JsonAlias
import com.khipster.template.khipstertemplate.modules.faces.Candidate
import com.khipster.template.khipstertemplate.modules.faces.Location

data class LunaGetEventsResponse(
    @JsonAlias("events") val events: List<LunaEventResponse>
)

data class LunaEventResponse(
    @JsonAlias("emotion") val emotion: Int?,
    @JsonAlias("face_detections") val faceDetections: List<LunaFaceDetection>?,
    @JsonAlias("mask") val mask: Int?,
    @JsonAlias("ethnic_group") val ethnicGroup: Int?,
    @JsonAlias("liveness") val liveness: Int?,
    @JsonAlias("deepfake") val deepfake: Int?,
    @JsonAlias("account_id") val accountId: String?,
    @JsonAlias("user_data") val userData: String?,
    @JsonAlias("create_time") val createTime: String?,
    @JsonAlias("end_time") val endTime: String?,
    @JsonAlias("track_id") val trackId: String?,
    @JsonAlias("event_id") val eventId: String?,
    @JsonAlias("external_id") val externalId: String?,
    @JsonAlias("handler_id") val handlerId: String?,
    @JsonAlias("attach_result") val attachResult: List<String>?,
    @JsonAlias("source") val source: String?,
    @JsonAlias("stream_id") val streamId: String?,
    @JsonAlias("tags") val tags: List<String>?,
    @JsonAlias("location") val location: Location?,
    @JsonAlias("meta") val meta: Map<String, Any>?,
    @JsonAlias("face_id") val faceId: String?,
    @JsonAlias("gender") val gender: Int?,
    @JsonAlias("age") val age: Int?,
    @JsonAlias("match_result") val matchResult: List<MatchResult>?,
    @JsonAlias("top_match") val topMatch: TopMatch?,
)

data class LunaFaceDetection(
    @JsonAlias("sample_id") val sampleId: String?,
    @JsonAlias("detect_time") val detectTime: String?,
    @JsonAlias("detect_ts") val detectTs: Double?,
    @JsonAlias("image_origin") val imageOrigin: String?,
    @JsonAlias("detection") val detection: Detection?
) {
    data class Detection(
        @JsonAlias("rect") val rect: Rect?
    )

    data class Rect(
        @JsonAlias("x") val x: Int?,
        @JsonAlias("y") val y: Int?,
        @JsonAlias("width") val width: Int?,
        @JsonAlias("height") val height: Int?
    )
}

data class TopMatch(
    @JsonAlias("face_id") val faceId: String?,
    @JsonAlias("similarity") val similarity: Double?,
    @JsonAlias("label") val label: String?,
    @JsonAlias("external_id") val externalId: String?
)

data class MatchResult(
    @JsonAlias("label") val label: String?,
    @JsonAlias("candidates") val candidates: List<Candidate>?
)

