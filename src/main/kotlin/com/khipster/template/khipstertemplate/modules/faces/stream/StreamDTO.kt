package com.khipster.template.khipstertemplate.modules.faces.stream

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import com.khipster.template.khipstertemplate.modules.faces.Location
import java.util.*

data class LunaStreamCreateRequest(
    @JsonProperty("account_id") val accountId: String = "",
    @JsonProperty("name") val name: String = "",
    @JsonProperty("status") val status: String = "pending",
    @JsonProperty("description") val description: String = "",
    @JsonProperty("data") val data: LunaStreamData,
    @JsonProperty("event_handler") val eventHandler: LunaStreamEventHandler,
    @JsonProperty("policies") val policies: LunaStreamPolicies,
    @JsonProperty("autorestart") val autorestart: LunaStreamAutorestart
)

data class LunaStreamData(
    @JsonProperty("type") val type: String = "udp",
    @JsonProperty("reference") val reference: String = "",
    @JsonProperty("roi") val roi: LunaStreamRegion,
    @JsonProperty("droi") val droi: LunaStreamRegion,
    @JsonProperty("rotation") val rotation: Int = 0,
    @JsonProperty("preferred_program_stream_frame_width") val preferredProgramStreamFrameWidth: Int = 800,
    @JsonProperty("endless") val endless: Boolean = true
)

data class LunaStreamRegion(
    @JsonProperty("x") val x: Int = 0,
    @JsonProperty("y") val y: Int = 0,
    @JsonProperty("width") val width: Int = 0,
    @JsonProperty("height") val height: Int = 0,
    @JsonProperty("mode") val mode: String = "abs"
)

data class LunaStreamEventHandler(
    @JsonProperty("origin") val origin: String = "",
    @JsonProperty("api_version") val apiVersion: Int = 6,
    @JsonProperty("bestshot_handler") val bestshotHandler: LunaStreamBestshotHandler,
    @JsonProperty("frame_store") val frameStore: String = ""
)

data class LunaStreamBestshotHandler(
    @JsonProperty("handler_id") val handlerId: String = UUID.randomUUID().toString()
)


data class LunaStreamPolicies(
    @JsonProperty("sending") val sending: LunaStreamSendingPolicy,
    @JsonProperty("primary_track_policy") val primaryTrackPolicy: LunaStreamPrimaryTrackPolicy,
    @JsonProperty("liveness") val liveness: LunaStreamLivenessPolicy,
    @JsonProperty("filtering") val filtering: LunaStreamFilteringPolicy,
    @JsonProperty("frame_processing_mode") val frameProcessingMode: String = "auto",
    @JsonProperty("real_time_mode_fps") val realTimeModeFps: Int = 0,
    @JsonProperty("ffmpeg_threads_number") val ffmpegThreadsNumber: Int = 0,
    @JsonProperty("healthcheck") val healthcheck: LunaStreamHealthcheck
)

data class LunaStreamSendingPolicy(
    @JsonProperty("time_period_of_searching") val timePeriodOfSearching: Int = -1,
    @JsonProperty("silent_period") val silentPeriod: Int = 0,
    @JsonProperty("type") val type: String = "frames",
    @JsonProperty("number_of_bestshots_to_send") val numberOfBestshotsToSend: Int = 0,
    @JsonProperty("send_only_full_set") val sendOnlyFullSet: Boolean = true,
    @JsonProperty("delete_track_after_sending") val deleteTrackAfterSending: Boolean = false
)

data class LunaStreamPrimaryTrackPolicy(
    @JsonProperty("use_primary_track_policy") val usePrimaryTrackPolicy: Boolean = false,
    @JsonProperty("best_shot_min_size") val bestShotMinSize: Int = 70,
    @JsonProperty("best_shot_proper_size") val bestShotProperSize: Int = 140
)

data class LunaStreamLivenessPolicy(
    @JsonProperty("use_mask_liveness_filtration") val useMaskLivenessFiltration: Boolean = false,
    @JsonProperty("use_flying_faces_liveness_filtration") val useFlyingFacesLivenessFiltration: Boolean = false,
    @JsonProperty("liveness_mode") val livenessMode: Int = 0,
    @JsonProperty("number_of_liveness_checks") val numberOfLivenessChecks: Int = 0,
    @JsonProperty("liveness_threshold") val livenessThreshold: Int = 0,
    @JsonProperty("livenesses_weights") val livenessesWeights: List<Int> = listOf(0, 0, 0),
    @JsonProperty("mask_backgrounds_count") val maskBackgroundsCount: Int = 0
)

data class LunaStreamFilteringPolicy(
    @JsonProperty("min_score") val minScore: Double = 0.5187,
    @JsonProperty("detection_yaw_threshold") val detectionYawThreshold: Int = 40,
    @JsonProperty("detection_pitch_threshold") val detectionPitchThreshold: Int = 40,
    @JsonProperty("detection_roll_threshold") val detectionRollThreshold: Int = 30,
    @JsonProperty("yaw_number") val yawNumber: Int = 1,
    @JsonProperty("yaw_collection_mode") val yawCollectionMode: Boolean = false,
    @JsonProperty("mouth_occlusion_threshold") val mouthOcclusionThreshold: Int = 0,
    @JsonProperty("min_body_size_threshold") val minBodySizeThreshold: Int = 0
)

data class LunaStreamHealthcheck(
    @JsonProperty("max_error_count") val maxErrorCount: Int = 10,
    @JsonProperty("period") val period: Int = 3600,
    @JsonProperty("retry_delay") val retryDelay: Int = 5
)

data class LunaStreamAutorestart(
    @JsonProperty("restart") val restart: Int = 1,
    @JsonProperty("attempt_count") val attemptCount: Int = 10,
    @JsonProperty("delay") val delay: Int = 60
)

data class LunaStreamCreateResponse(
    @JsonAlias("version") val version: Int = 1,
    @JsonAlias("stream_id") val streamId: String = "",
)

data class LunaStreamGetByIdResponse(
    @JsonAlias("stream_id") val streamId: String,
    @JsonAlias("account_id") val accountId: String,
    @JsonAlias("name") val name: String,
    @JsonAlias("description") val description: String,
    @JsonAlias("data") val data: LunaStreamData,
    @JsonAlias("location") val location: Location,
    @JsonAlias("autorestart") val autorestart: LunaStreamAutorestart,
    @JsonAlias("status") val status: String = "pending",
    @JsonAlias("version") val version: Int = 1,
    @JsonAlias("create_time") val createTime: String,
    @JsonAlias("event_handler") val eventHandler: LunaStreamEventHandler,
    @JsonAlias("policies") val policies: LunaStreamPolicies,
    @JsonAlias("last_error") val lastError: Any? = null,
    @JsonAlias("video_info") val videoInfo: Any? = null,
    @JsonAlias("preview") val preview: Any? = null,
    @JsonAlias("groups") val groups: List<Any?>
)

data class LunaStreamsCountResponse(
    @JsonAlias("streams_count") val streamsCount: Int = 0,
)

data class LunaStreamsGetResponse(
    val streams: List<LunaStreamGetByIdResponse> = emptyList(),
)
