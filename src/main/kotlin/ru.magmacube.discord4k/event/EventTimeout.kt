package ru.magmacube.discord4k.event

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

sealed class EventTimeout(val time: Duration) {
    object Inherit : EventTimeout(Duration.ZERO)
    class Limit(time: Duration) : EventTimeout(time)
}
fun Long?.toTimeout() = this?.milliseconds?.toTimeout()

/**
 * Convert this long to [EventTimeout.Limit] or [EventTimeout.Inherit] on null
 */
fun Duration?.toTimeout() = this?.let { EventTimeout.Limit(it) } ?: EventTimeout.Inherit