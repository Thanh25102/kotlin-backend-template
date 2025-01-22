package com.khipster.template.khipstertemplate.modules.faces

import com.fasterxml.jackson.annotation.JsonProperty

data class LunaFaceDetectionEvent(
    @JsonProperty("event") val event: LunaFaceDetectionEventDetail?,
    @JsonProperty("handler_id") val handlerId: String?,
    @JsonProperty("event-create-time") val eventCreateTime: String?,
    @JsonProperty("event-end-time") val eventEndTime: String?,
    @JsonProperty("Luna-Request-id") val lunaRequestId: String
)

data class LunaFaceDetectionEventDetail(
    @JsonProperty("face_attributes") val faceAttributes: FaceAttributes?,
    @JsonProperty("body_attributes") val bodyAttributes: BodyAttributes?,
    @JsonProperty("source") val source: String?,
    @JsonProperty("stream_id") val streamId: String?,
    @JsonProperty("tags") val tags: List<String>?,
    @JsonProperty("event_id") val eventId: String?,
    @JsonProperty("url") val url: String?,
    @JsonProperty("matches") val matches: List<Match>?,
    @JsonProperty("external_id") val externalId: String?,
    @JsonProperty("user_data") val userData: String?,
    @JsonProperty("location") val location: Location?,
    @JsonProperty("detections") val detections: List<Detection>?,
    @JsonProperty("meta") val meta: Map<String?, Any>?,
    @JsonProperty("aggregate_estimations") val aggregateEstimations: AggregateEstimations?,
    @JsonProperty("track_id") val trackId: String?,
    @JsonProperty("face") val face: Face?
)

data class FaceAttributes(
    @JsonProperty("attribute_id") val attributeId: String?,
    @JsonProperty("basic_attributes") val basicAttributes: BasicAttributes?,
    @JsonProperty("score") val score: Double?,
    @JsonProperty("url") val url: String?,
    @JsonProperty("samples") val samples: List<String>
)

data class BasicAttributes(
    @JsonProperty("age") val age: Int?,
    @JsonProperty("gender") val gender: Int?,
    @JsonProperty("ethnicities") val ethnicities: Ethnicities
)

data class Ethnicities(
    @JsonProperty("estimations") val estimations: Map<String?, Double>?,
    @JsonProperty("predominant_ethnicity") val predominantEthnicity: String
)

data class BodyAttributes(
    @JsonProperty("score") val score: Double?,
    @JsonProperty("samples") val samples: List<String>
)

data class Match(
    @JsonProperty("label") val label: String?,
    @JsonProperty("candidates") val candidates: List<Candidate>
)

data class Candidate(
    @JsonProperty("face") val face: CandidateFace?,
    @JsonProperty("event") val event: CandidateEvent?,
    @JsonProperty("similarity") val similarity: Double?
)

data class CandidateFace(
    @JsonProperty("face_id") val faceId: String?,
    @JsonProperty("external_id") val externalId: String?,
    @JsonProperty("account_id") val accountId: String?,
    @JsonProperty("user_data") val userData: String?,
    @JsonProperty("create_time") val createTime: String?,
    @JsonProperty("lists") val lists: List<String>?,
    @JsonProperty("avatar") val avatar: String?,
    @JsonProperty("event_id") val eventId: String?
)

data class CandidateEvent(
    @JsonProperty("event_id") val eventId: String?,
    @JsonProperty("external_id") val externalId: String?,
    @JsonProperty("user_data") val userData: String?,
    @JsonProperty("create_time") val createTime: String?,
    @JsonProperty("handler_id") val handlerId: String?,
    @JsonProperty("source") val source: String?
)

data class Location(
    @JsonProperty("city") val city: String?,
    @JsonProperty("area") val area: String?,
    @JsonProperty("district") val district: String?,
    @JsonProperty("street") val street: String?,
    @JsonProperty("house_number") val houseNumber: String?,
    @JsonProperty("geo_position") val geoPosition: GeoPosition?
)

data class GeoPosition(
    @JsonProperty("longitude") val longitude: Double?,
    @JsonProperty("latitude") val latitude: Double?
)

data class Detection(
    @JsonProperty("filename") val filename: String?,
    @JsonProperty("samples") val samples: Samples?,
    @JsonProperty("detect_time") val detectTime: String?,
    @JsonProperty("detect_ts") val detectTs: Double?,
    @JsonProperty("image_origin") val imageOrigin: String?
)

data class Samples(
    @JsonProperty("face") val face: FaceSample?,
    @JsonProperty("body") val body: BodySample?
)

data class FaceSample(
    @JsonProperty("sample_id") val sampleId: String?,
    @JsonProperty("detection") val detection: DetectionAttributes?,
    @JsonProperty("url") val url: String?
)

data class BodySample(
    @JsonProperty("sample_id") val sampleId: String?,
    @JsonProperty("detection") val detection: DetectionBody?,
    @JsonProperty("url") val url: String?
)

data class DetectionAttributes(
    @JsonProperty("attributes") val attributes: Map<String?, Any>?,
    @JsonProperty("quality") val quality: Quality?,
    @JsonProperty("rect") val rect: Rect?,
    @JsonProperty("landmarks5") val landmarks5: List<List<Int>>?,
    @JsonProperty("landmarks68") val landmarks68: List<List<Int>>?,
    @JsonProperty("face_quality") val faceQuality: FaceQuality?
)

data class DetectionBody(
    @JsonProperty("rect") val rect: Rect?,
    @JsonProperty("attributes") val attributes: Map<String?, Any>?
)

data class Quality(
    @JsonProperty("blurriness") val blurriness: Double?,
    @JsonProperty("dark") val dark: Double?,
    @JsonProperty("illumination") val illumination: Double?,
    @JsonProperty("specularity") val specularity: Double?,
    @JsonProperty("light") val light: Double?
)

data class Rect(
    @JsonProperty("x") val x: Int?,
    @JsonProperty("y") val y: Int?,
    @JsonProperty("width") val width: Int?,
    @JsonProperty("height") val height: Int?
)

data class FaceQuality(
    @JsonProperty("status") val status: Int?,
    @JsonProperty("checks") val checks: List<FaceQualityCheck>?
)

data class FaceQualityCheck(
    @JsonProperty("name") val name: String?,
    @JsonProperty("object_value") val objectValue: Any?,
    @JsonProperty("threshold_value") val thresholdValue: Any?,
    @JsonProperty("result") val result: Int?
)

data class AggregateEstimations(
    @JsonProperty("face") val face: FaceEstimation?,
    @JsonProperty("body") val body: BodyEstimation?
)

data class FaceEstimation(
    @JsonProperty("attributes") val attributes: Map<String?, Any>?
)

data class BodyEstimation(
    @JsonProperty("attributes") val attributes: Map<String?, Any>?
)

data class Face(
    @JsonProperty("external_id") val externalId: String?,
    @JsonProperty("face_id") val faceId: String?,
    @JsonProperty("url") val url: String?,
    @JsonProperty("lists") val lists: List<String>?,
    @JsonProperty("user_data") val userData: String?,
    @JsonProperty("avatar") val avatar: String?,
    @JsonProperty("event_id") val eventId: String?
)