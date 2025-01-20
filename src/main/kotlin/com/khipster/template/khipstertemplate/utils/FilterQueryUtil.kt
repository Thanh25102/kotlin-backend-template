package com.khipster.template.khipstertemplate.utils

import org.springframework.web.util.UriComponentsBuilder
import tech.jhipster.service.filter.Filter
import tech.jhipster.service.filter.RangeFilter
import tech.jhipster.service.filter.StringFilter

object FilterURLQueryService {
    fun buildURLQueryFromFilters(builder: UriComponentsBuilder, fieldName: String, filters: Any?): String {
        try {
            when (filters) {
                is StringFilter -> addStringFilterQueries(builder, fieldName, filters)
                is RangeFilter<*> -> addRangeFilterQueries(builder, fieldName, filters)
                is Filter<*> -> addFilterQueries(builder, fieldName, filters)
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return builder.toUriString()
    }

    fun <FIELD_TYPE : Comparable<FIELD_TYPE>, FILTER : RangeFilter<FIELD_TYPE>> addRangeFilterQueries(
        builder: UriComponentsBuilder,
        fieldName: String,
        filter: FILTER
    ) {
        addFilterQuery(builder, fieldName, "greaterThan", filter.greaterThan)
        addFilterQuery(builder, fieldName, "lessThan", filter.lessThan)
        addFilterQuery(builder, fieldName, "greaterThanOrEqual", filter.greaterThanOrEqual)
        addFilterQuery(builder, fieldName, "lessThanOrEqual", filter.lessThanOrEqual)
        addFilterQuery(builder, fieldName, "equals", filter.equals)
        addFilterQuery(builder, fieldName, "notEquals", filter.notEquals)
        addFilterQuery(builder, fieldName, "specified", filter.specified)
        addCollectionFilterQuery(builder, fieldName, "in", filter.getIn())
        addCollectionFilterQuery(builder, fieldName, "notIn", filter.notIn)
    }

    fun addStringFilterQueries(builder: UriComponentsBuilder, fieldName: String, filter: StringFilter) {
        addFilterQuery(builder, fieldName, "equals", filter.equals)
        addFilterQuery(builder, fieldName, "notEquals", filter.notEquals)
        addFilterQuery(builder, fieldName, "specified", filter.specified)
        addFilterQuery(builder, fieldName, "contains", filter.contains)
        addFilterQuery(builder, fieldName, "doesNotContain", filter.doesNotContain)
        addCollectionFilterQuery(builder, fieldName, "in", filter.getIn())
        addCollectionFilterQuery(builder, fieldName, "notIn", filter.notIn)
    }

    fun <FILTER : Filter<*>> addFilterQueries(
        builder: UriComponentsBuilder,
        fieldName: String,
        filter: FILTER
    ) {
        addFilterQuery(builder, fieldName, "equals", filter.equals)
        addFilterQuery(builder, fieldName, "notEquals", filter.notEquals)
        addFilterQuery(builder, fieldName, "specified", filter.specified)
        addCollectionFilterQuery(builder, fieldName, "in", filter.getIn())
        addCollectionFilterQuery(builder, fieldName, "notIn", filter.notIn)
    }

    fun <T> addFilterQuery(
        builder: UriComponentsBuilder,
        fieldName: String,
        queryParamSuffix: String,
        value: T?
    ) {
        value?.let { builder.queryParam("$fieldName.$queryParamSuffix", it) }
    }

    fun <T> addCollectionFilterQuery(
        builder: UriComponentsBuilder,
        fieldName: String,
        queryParamSuffix: String,
        values: Collection<T>?
    ) {
        values?.forEach { builder.queryParam("$fieldName.$queryParamSuffix", it) }
    }
}