package com.khipster.template.khipstertemplate.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class LocalDateToInstantDeserializer : JsonDeserializer<Instant>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Instant? {
        val localDate = p?.text?.let { LocalDate.parse(it) }
        return localDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()
    }
}