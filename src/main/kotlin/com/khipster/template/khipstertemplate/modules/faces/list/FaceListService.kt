package com.khipster.template.khipstertemplate.modules.faces.list

interface FaceListService {

    fun create(face: FaceListCreate): LunaListCreateResponse?

    fun update(id: String, face: LunaListUpdateRequest)

    fun addFaceListToBranch(listId: String, branchId: Long)

}