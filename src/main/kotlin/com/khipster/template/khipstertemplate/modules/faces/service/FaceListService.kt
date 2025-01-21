package com.khipster.template.khipstertemplate.modules.faces.service

import com.khipster.template.khipstertemplate.modules.faces.*

interface FaceListService {

    fun create(createListDto: CreateListDto): CreateListResponse

    fun findAll(faceListCriteria: FaceListCriteria): List<ListData>

    fun count(faceListCriteria: FaceListCriteria): Long

    fun update(updateListDto: UpdateListDto): ListData

}