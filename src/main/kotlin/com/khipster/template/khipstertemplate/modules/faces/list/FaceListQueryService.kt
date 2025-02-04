package com.khipster.template.khipstertemplate.modules.faces.list

import com.khipster.template.khipstertemplate.utils.addFilters
import com.khipster.template.khipstertemplate.utils.addPageable
import com.khipster.template.khipstertemplate.utils.addQueryParam
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.util.UriComponentsBuilder

@Service
class FaceListQueryService(
    @Qualifier("visionLabsClient") private val restClient: RestClient,
    private val faceListBranchRepo: FaceListBranchRepo
) {

    fun fetchByCriteria(criteria: FaceListCriteria?): String? {
        return buildQueryString(criteria)
    }


    fun fetchFaceListsNotExistInBranchId(branchId: Long): List<LunaListResponse>? {
        val builder = UriComponentsBuilder.newInstance()
        builder.addPageable(Pageable.ofSize(1000))
        val queryString = builder.build().toUriString()

        println("result $queryString")

        val result = restClient.get().uri("/lp5/6/lists$queryString").retrieve().body(LunaListsResponse::class.java)

        println("result $result")

        val listIds = faceListBranchRepo.findByBranchId(branchId)
            .mapNotNull { it.listId }
            .toSet()
        println("listIds $listIds")

        return result?.lists?.filter { !listIds.contains(it.listId) }
    }

    fun fetchByCriteria(criteria: FaceListCriteria?, pageable: Pageable): LunaListsResponse? {
        try {
            val queryString = buildQueryString(criteria, pageable)
            val result = restClient.get().uri("/lp5/6/lists$queryString").retrieve().body(LunaListsResponse::class.java)
            return result
        } catch (e: RuntimeException) {
            return LunaListsResponse(
                lists = emptyList()
            )
        }
    }

    fun countByCriteria(criteria: FaceListCriteria?): Int? {
        try {
            val queryString = buildQueryString(criteria)
            val result = restClient.get().uri("/lp5/6/lists/count$queryString").retrieve().body(Int::class.java)
            return result
        } catch (e: RuntimeException) {
            return 0
        }
    }

    private fun buildQueryString(criteria: FaceListCriteria? = null, pageable: Pageable? = null): String {
        val builder = UriComponentsBuilder.newInstance()
        if (criteria == null) return builder.build().toUriString()

        val (createTime, lastUpdateTime, userData, branchId) = criteria

        if (branchId != null) {
            val branchGroup = faceListBranchRepo.findByBranchId(branchId.equals)
            val listIds = branchGroup
                .mapNotNull { it.listId }
                .joinToString(separator = ",")
            if (listIds.isEmpty())
                throw RuntimeException("List not found")
            builder.addQueryParam("list_ids", listIds)
        }

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