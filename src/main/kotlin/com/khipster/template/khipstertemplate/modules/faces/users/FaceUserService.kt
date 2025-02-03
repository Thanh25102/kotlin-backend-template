package com.khipster.template.khipstertemplate.modules.faces.users

import org.springframework.web.multipart.MultipartFile

interface FaceUserService {
    fun detectFace(image: MultipartFile): DetectionResponse?
    fun createFace(face: FaceCreateRequest): FaceCreateResponse?
    fun updateFace(face: LunaFacesUpdateRequest)
}