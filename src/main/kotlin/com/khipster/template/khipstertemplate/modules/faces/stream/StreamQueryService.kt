package com.khipster.template.khipstertemplate.modules.faces.stream

import com.khipster.template.khipstertemplate.utils.addFilters
import com.khipster.template.khipstertemplate.utils.addPageable
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.util.UriComponentsBuilder

@Service
class StreamQueryService(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) {
    fun fetchByCriteria(criteria: StreamCriteria?): String? {
        return buildQueryString(criteria)
    }

    fun fetchByCriteria(criteria: StreamCriteria?, pageable: Pageable): LunaStreamsGetResponse? {
        val queryString = buildQueryString(criteria, pageable)
        val result = restClient.get().uri("/lp5/6/luna-streams/1/strems$queryString").retrieve().body(LunaStreamsGetResponse::class.java)
        return result
    }

    fun countByCriteria(criteria: StreamCriteria?): Int? {
        val queryString = buildQueryString(criteria)
        val result = restClient.get().uri("/lp5/6/luna-streams/1/strems/count$queryString").retrieve().body(Int::class.java)
        return result
    }

    private fun buildQueryString(criteria: StreamCriteria? = null, pageable: Pageable? = null): String {
        val builder = UriComponentsBuilder.newInstance()
        if (criteria == null) return builder.build().toUriString()

        val (createTime, lastUpdateTime) = criteria
        builder.addFilters(
            mapOf(
                "create_time" to createTime,
                "last_update_time" to lastUpdateTime,
            )
        )
        builder.addPageable(pageable)
        return builder.build().toUriString()
    }
}