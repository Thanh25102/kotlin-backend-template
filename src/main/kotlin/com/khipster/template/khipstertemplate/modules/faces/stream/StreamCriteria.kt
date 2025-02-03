package com.khipster.template.khipstertemplate.modules.faces.stream

import tech.jhipster.service.filter.InstantFilter
import tech.jhipster.service.filter.StringFilter

data class StreamCriteria(
    var names: StringFilter? = null,
    var createTime: InstantFilter? = null,
    var lastUpdateTime: InstantFilter? = null,
    var group: StringFilter? = null,
) {
    constructor(other: StreamCriteria) : this(
        other.names?.copy(),
        other.createTime?.copy(),
        other.lastUpdateTime?.copy(),
        other.group?.copy()
    )

    fun copy() = StreamCriteria(this)
}