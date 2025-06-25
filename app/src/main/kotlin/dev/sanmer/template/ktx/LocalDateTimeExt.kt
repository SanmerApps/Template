package dev.sanmer.template.ktx

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun Long.toLocalDateTime(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
) = Instant.fromEpochMilliseconds(this).toLocalDateTime(timeZone)