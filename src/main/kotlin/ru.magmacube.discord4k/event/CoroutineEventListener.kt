package ru.magmacube.discord4k.event

import net.dv8tion.jda.api.events.GenericEvent

fun interface CoroutineEventListener {
    val timeout: EventTimeout get() = EventTimeout.Inherit

    suspend fun onEvent(event: GenericEvent)

    /**
     * Unregisters this listener
     */
    fun cancel() {}
}