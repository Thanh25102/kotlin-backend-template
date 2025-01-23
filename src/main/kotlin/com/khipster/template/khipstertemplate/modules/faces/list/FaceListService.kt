package com.khipster.template.khipstertemplate.modules.faces.list

interface FaceListService {

    fun create(face: LunaListCreateRequest): LunaListCreateResponse?

    fun update(face: LunaListUpdateRequest)

}