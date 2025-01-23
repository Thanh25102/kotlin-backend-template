package com.khipster.template.khipstertemplate.modules.faces.list

import com.khipster.template.khipstertemplate.utils.addFilters
import com.khipster.template.khipstertemplate.utils.addPageable
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.util.UriComponentsBuilder

@Service
class FaceListQueryService(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) {

    fun fetchByCriteria(criteria: FaceListCriteria?): String? {
        return buildQueryString(criteria)
    }

    fun fetchByCriteria(criteria: FaceListCriteria?, pageable: Pageable): LunaListsResponse? {
        val queryString = buildQueryString(criteria, pageable)
        val result = restClient.get().uri("/lp5/6/lists$queryString").retrieve().body(LunaListsResponse::class.java)
        return result
    }

    private fun buildQueryString(criteria: FaceListCriteria? = null, pageable: Pageable? = null): String {
        val builder = UriComponentsBuilder.newInstance()
        if (criteria == null) return builder.build().toUriString()

        val (createTime, lastUpdateTime, userData) = criteria
        builder.addFilters(
            mapOf(
                "create_time" to createTime,
                "last_update_time" to lastUpdateTime,
                "user_data" to userData
            )
        )
        builder.addPageable(pageable)
        return builder.build().toUriString()
    }
}