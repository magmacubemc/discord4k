package ru.magmacube.discord4k

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.GenericEvent
import ru.magmacube.discord4k.event.CoroutineEventListener
import ru.magmacube.discord4k.event.CoroutineEventManager
import kotlin.time.Duration

class Client internal constructor(val jda: JDA) {
    val timeout = Duration.INFINITE
    val eventManager = CoroutineEventManager(timeout = timeout)
    inline fun <reified T : GenericEvent> on(crossinline block: CoroutineEventListener.(T) -> Unit) = eventManager.on(timeout, block)
    val scope get() = eventManager
    
}