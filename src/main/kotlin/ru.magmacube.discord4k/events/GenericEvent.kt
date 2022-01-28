package ru.magmacube.discord4k.events

import net.dv8tion.jda.api.events.GenericEvent
import ru.magmacube.discord4k.Client

interface GenericEvent {
    val client: Client
    val responseNumber: Long
}