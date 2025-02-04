package com.khipster.template.khipstertemplate.modules.faces.users

fun FaceCreateRequest.toLunaMatterRequest(): LunaMatcherFaceRequest {
    return LunaMatcherFaceRequest(
        references = this.sampleIds?.map {
            LunaMatcherFaceRequest.Reference(
                type = "sdk_descriptor",
                data = this.sdkDescriptor,
                id = it
            )
        },
        candidates = listOf(
            this.lists?.map {
                LunaMatcherFaceRequest.Candidate(
                    filters = LunaMatcherFaceRequest.Candidate.Filters(
                        origin = "faces",
//                        listId = it
                    ),
                    limit = 1,
                    threshold = if (this.externalId == null) 0.05F else null
                )
            } ?: emptyList(),
            listOf(
                LunaMatcherFaceRequest.Candidate(
                    filters = LunaMatcherFaceRequest.Candidate.Filters(
                        origin = "faces",
                        externalIds = if (this.externalId != null) listOf(this.externalId) else null
                    ),
                    limit = 1,
                )
            )
        ).flatten()
    )
}
