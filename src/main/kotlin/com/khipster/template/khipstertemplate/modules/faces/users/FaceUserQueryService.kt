package com.khipster.template.khipstertemplate.modules.faces.users

import com.khipster.template.khipstertemplate.utils.addFilters
import com.khipster.template.khipstertemplate.utils.addPageable
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.util.UriComponentsBuilder

@Service
class FaceUserQueryService(
    @Qualifier("visionLabsClient") private val restClient: RestClient
) {

    fun fetchByCriteria(criteria: FaceUserCriteria?): String? {
        return buildQueryString(criteria)
    }

    fun fetchByCriteria(criteria: FaceUserCriteria?, pageable: Pageable): LunaFacesResponse? {
        val queryString = buildQueryString(criteria, pageable)
        val result = restClient.get().uri("/lp5/6/faces$queryString").retrieve().body(LunaFacesResponse::class.java)
        return result
    }

    fun fetchCountByCriteria(criteria: FaceUserCriteria?): Int? {
        val queryString = buildQueryString(criteria)
        val result = restClient.get().uri("/lp5/6/faces/count$queryString").retrieve().body(Int::class.java)
        return result
    }

    private fun buildQueryString(criteria: FaceUserCriteria? = null, pageable: Pageable? = null): String {
        val builder = UriComponentsBuilder.newInstance()
        if (criteria == null) return builder.build().toUriString()

        val (
            accountId, createTime, eventId, faceId, faceIds, externalIds, userData, listId,
        ) = criteria
        builder.addFilters(
            mapOf(
                "account_id" to accountId,
                "create_time" to createTime,
                "event_id" to eventId,
                "face_id" to faceId,
                "face_ids" to faceIds,
                "external_ids" to externalIds,
                "user_data" to userData,
                "list_id" to listId,
            )
        )
        builder.addPageable(pageable)

        return builder.build().toUriString()
    }
}