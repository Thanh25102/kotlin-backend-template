package com.khipster.template.khipstertemplate.utils

import org.springframework.data.domain.Pageable
import org.springframework.web.util.UriComponentsBuilder
import tech.jhipster.service.filter.Filter
import tech.jhipster.service.filter.RangeFilter
import tech.jhipster.service.filter.StringFilter

fun UriComponentsBuilder.addFilters(filters: Map<String, Any?>) {
    filters.forEach { (fieldName, filter) -> addFilter(fieldName, filter) }
}

fun UriComponentsBuilder.addFilter(fieldName: String, filter: Any?) {
    when (filter) {
        is StringFilter -> this.addStringFilter(fieldName, filter)
        is RangeFilter<*> -> this.addRangeFilter(fieldName, filter)
        is Filter<*> -> this.addGenericFilter(fieldName, filter)
    }
}

fun UriComponentsBuilder.addStringFilter(fieldName: String, filter: StringFilter) {
    addGenericFilter(fieldName, filter)
    addQueryParam("${fieldName}__like", filter.contains)
    addQueryParam("${fieldName}__nlike", filter.doesNotContain)
}

fun <T : Comparable<T>> UriComponentsBuilder.addRangeFilter(fieldName: String, filter: RangeFilter<T>) {
    addGenericFilter(fieldName, filter)
    addQueryParam("${fieldName}__gt", filter.greaterThan)
    addQueryParam("${fieldName}__lt", filter.lessThan)
    addQueryParam("${fieldName}__gte", filter.greaterThanOrEqual)
    addQueryParam("${fieldName}__lte", filter.lessThanOrEqual)
}

fun <T> UriComponentsBuilder.addGenericFilter(fieldName: String, filter: Filter<T>) {
    addQueryParam("${fieldName}__eq", filter.equals)
    addQueryParam("${fieldName}__neq", filter.notEquals)
    addQueryParam("${fieldName}__specified", filter.specified)
    filter.getIn()?.forEach { addQueryParam("${fieldName}__in", it) }
    filter.notIn?.forEach { addQueryParam("${fieldName}__nin", it) }
}

fun <T> UriComponentsBuilder.addQueryParam(paramName: String, value: T?) {
    value?.let { this.queryParam(paramName, it) }
}

// add pageable to uri from pageable of spring
fun UriComponentsBuilder.addPageable(pageable: Pageable?) {
    this.addQueryParam("page", pageable?.pageNumber ?: 0)
    this.addQueryParam("page_size", pageable?.pageSize ?: 10)
}