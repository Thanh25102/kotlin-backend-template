package com.khipster.template.khipstertemplate.modules.faces.detection

import com.khipster.template.khipstertemplate.modules.faces.Candidate
import com.khipster.template.khipstertemplate.modules.faces.Location

data class LunaGetEventsResponse(
     val events: List<LunaEventResponse>
)

data class LunaEventResponse(
     val emotion: Int?,
     val faceDetections: List<LunaFaceDetection>?,
     val mask: Int?,
     val ethnicGroup: Int?,
     val liveness: Int?,
     val deepfake: Int?,
     val accountId: String?,
     val userData: String?,
     val createTime: String?,
     val endTime: String?,
     val trackId: String?,
     val eventId: String?,
     val externalId: String?,
     val handlerId: String?,
     val attachResult: List<String>?,
     val source: String?,
     val streamId: String?,
     val tags: List<String>?,
     val location: Location?,
     val meta: Map<String, Any>?,
     val faceId: String?,
     val gender: Int?,
     val age: Int?,
     val matchResult: List<MatchResult>?,
     val topMatch: TopMatch?,
)

data class LunaFaceDetection(
     val sampleId: String?,
     val detectTime: String?,
     val detectTs: Double?,
     val imageOrigin: String?,
     val detection: Detection?
) {
    data class Detection(
         val rect: Rect?
    )

    data class Rect(
         val x: Int?,
         val y: Int?,
         val width: Int?,
         val height: Int?
    )
}

data class TopMatch(
     val faceId: String?,
     val similarity: Double?,
     val label: String?,
     val externalId: String?
)

data class MatchResult(
     val label: String?,
     val candidates: List<Candidate>?
)

