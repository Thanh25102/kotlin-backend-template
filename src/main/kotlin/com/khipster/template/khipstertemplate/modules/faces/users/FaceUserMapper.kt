package com.khipster.template.khipstertemplate.modules.faces.users

fun FaceCreateRequest.toLunaMatterRequest(): LunaMatcherFaceRequest {
    return LunaMatcherFaceRequest(
        references = this.sampleIds?.mapNotNull {
            LunaMatcherFaceRequest.Reference(
                type = "sdk_descriptor",
                data = this.sdkDescriptor,
                id = it
            )
        },
        candidates = listOf(
            this.lists?.mapNotNull {
                LunaMatcherFaceRequest.Candidate(
                    filters = LunaMatcherFaceRequest.Candidate.Filters(
                        origin = "faces",
                        listId = it
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
        ).flatMap { it }

//        candidates = this.lists?.toMutableList()?.mapNotNull {
//            LunaMatcherFaceRequest.Candidate(
//                filters = LunaMatcherFaceRequest.Candidate.Filters(
//                    origin = "faces",
//                    listId = it
//                ),
//                limit = 1,
//                threshold = if (this.externalId == null) 0.05F else null
//            )
//        }?.toMutableList()?.addAll(
//            listOf(
//                LunaMatcherFaceRequest.Candidate(
//                    filters = LunaMatcherFaceRequest.Candidate.Filters(
//                        origin = "faces",
//                        externalIds = if (this.externalId != null) listOf(this.externalId) else null
//                    ),
//                    limit = 1,
//                )
//            )
//        ) ?: emptyList()
    )
}
