package ru.magmacube.discord4k

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.GenericEvent
import ru.magmacube.discord4k.event.CoroutineEventManager

class Client internal constructor(val jda: JDA) {
    val eventManager = CoroutineEventManager()
    inline fun <T : GenericEvent> on(crossinline block: CoroutineEventManager.(T) -> Unit) {

    }
}