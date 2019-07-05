package com.necatisozer.common.extension

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException

fun tryParseDate(dateTime: String?) = dateTime?.let {
    try {
        val instant = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(it))
        ZonedDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()))
    } catch (exception: DateTimeParseException) {
        ZonedDateTime.parse(it, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    } catch (exception: DateTimeParseException) {
        null
    }
}