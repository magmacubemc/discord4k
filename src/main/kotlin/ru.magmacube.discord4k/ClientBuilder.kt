package ru.magmacube.discord4k

import net.dv8tion.jda.api.JDABuilder
import ru.magmacube.discord4k.client.GatewayIntents
import kotlin.time.Duration


inline fun default(token: String, crossinline builder: ClientBuilder.() -> Unit) = ClientBuilder.def(token).apply(builder).build()

class ClientBuilder internal constructor(token: String) {
    var intents = GatewayIntents()
    var token: String = token
    fun build() = Client(JDABuilder.createDefault(token).apply {
        setEnabledIntents(intents.intents)

    }.build())

    companion object {
        fun def(token: String) = ClientBuilder(token)
    }
}