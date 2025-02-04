package com.khipster.template.khipstertemplate.modules.faces.list

import com.khipster.template.khipstertemplate.config.toJsonSnakeCase
import com.khipster.template.khipstertemplate.modules.attendance.branch.BranchRepo
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate

@Service
@Transactional
class FaceListServiceImpl(
    @Qualifier("visionLabsClient") private val restClient: RestClient,
    @Qualifier("visionLabsTemplate") private val restTemplate: RestTemplate,
    private val faceListBranchRepo: FaceListBranchRepo,
    private val branchRepo: BranchRepo
) : FaceListService {

    override fun create(face: FaceListCreate): LunaListCreateResponse? {
        var faceListSaved: LunaListCreateResponse? = null

        branchRepo.findByIdOrNull(face.branchId)?.let {
            faceListSaved = restClient.post().uri("/lp5/6/lists")
                .contentType(MediaType.APPLICATION_JSON)
                .body(LunaListCreateRequest(userData = face.userData).toJsonSnakeCase())
                .retrieve()
                .body(LunaListCreateResponse::class.java)
            faceListBranchRepo.save(FaceListBranch(branch = it, listId = faceListSaved?.listId))
        }
        return faceListSaved
    }

    override fun update(id: String, face: LunaListUpdateRequest) {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val requestEntity = HttpEntity(LunaListCreateRequest(userData = face.userData), headers)
        restTemplate.patchForObject("/lp5/6/lists/${id}", requestEntity, Void::class.java)
    }
}