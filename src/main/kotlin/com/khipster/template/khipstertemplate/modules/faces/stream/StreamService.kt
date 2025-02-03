package com.khipster.template.khipstertemplate.modules.faces.stream

interface StreamService {
    fun createStream(stream: LunaStreamCreateRequest): LunaStreamCreateResponse?
    fun updateStream(id: String, stream: LunaStreamCreateRequest)
    fun deleteStream(id: String)
}