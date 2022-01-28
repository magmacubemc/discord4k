package ru.magmacube.discord4k.interactions

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import ru.magmacube.discord4k.Base
import ru.magmacube.discord4k.Client
import ru.magmacube.discord4k.event.CoroutineEventListener

class InteractionManager internal constructor(override val client: Client) : Base {
    inline fun onCommand(crossinline consumer: suspend CoroutineEventListener.(SlashCommandEvent) -> Unit) = client.eventManager.on<SlashCommandEvent> {  }
}