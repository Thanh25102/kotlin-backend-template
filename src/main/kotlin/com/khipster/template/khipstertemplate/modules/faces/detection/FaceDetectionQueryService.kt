package com.khipster.template.khipstertemplate.modules.faces.detection

import com.khipster.template.khipstertemplate.module.detection.LunaGetEventsResponse
import com.khipster.template.khipstertemplate.utils.addFilters
import com.khipster.template.khipstertemplate.utils.addPageable
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.util.UriComponentsBuilder

@Service
class FaceDetectionQueryService(@Qualifier("visionLabsClient") private val restClient: RestClient) {

    fun fetchByCriteria(criteria: FaceDetectionCriteria?): String? {
        return buildQueryString(criteria)
    }

    fun fetchByCriteria(criteria: FaceDetectionCriteria?, pageable: Pageable): LunaGetEventsResponse? {
        val queryString = buildQueryString(criteria, pageable)
        val result = restClient.get().uri("/lp5/6/events$queryString").retrieve().body(LunaGetEventsResponse::class.java)
        return result
    }

    private fun buildQueryString(criteria: FaceDetectionCriteria? = null, pageable: Pageable? = null): String {
        val builder = UriComponentsBuilder.newInstance()
        if (criteria == null) return builder.build().toUriString()

        val (createTime, insertTime, sources, streamIds, cities, areas, districts, gender) = criteria
        builder.addFilters(
            mapOf(
                "create_time" to createTime,
                "insert_time" to insertTime,
                "sources" to sources,
                "stream_ids" to streamIds,
                "cities" to cities,
                "areas" to areas,
                "districts" to districts,
                "gender" to gender
            )
        )

        builder.addPageable(pageable)

        return builder.build().toUriString()
    }
}