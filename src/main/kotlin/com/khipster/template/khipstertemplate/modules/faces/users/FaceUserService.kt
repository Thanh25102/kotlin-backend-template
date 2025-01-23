package com.khipster.template.khipstertemplate.modules.faces.users

import org.springframework.web.multipart.MultipartFile

interface FaceUserService {
    fun detectFace(image: MultipartFile): LunaFaceResponse?
    fun createFace(face: LunaFacesCreateRequest): LunaFaceCreateResponse?
    fun updateFace(face: LunaFacesUpdateRequest)
}