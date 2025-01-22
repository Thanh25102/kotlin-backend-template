package com.khipster.template.khipstertemplate.modules.faces.users

interface FaceUserService {
    fun createFace(face: LunaFacesCreateRequest): LunaFaceCreateResponse?
    fun updateFace(face: LunaFacesUpdateRequest)
}