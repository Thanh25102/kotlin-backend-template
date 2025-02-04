package com.khipster.template.khipstertemplate.modules.faces.stream

import tech.jhipster.service.filter.InstantFilter
import tech.jhipster.service.filter.StringFilter

data class StreamCriteria(
    var createTime: InstantFilter? = null,
    var lastUpdateTime: InstantFilter? = null,
) {
    constructor(other: StreamCriteria) : this(
        other.createTime?.copy(),
        other.lastUpdateTime?.copy(),
    )

    fun copy() = StreamCriteria(this)
}