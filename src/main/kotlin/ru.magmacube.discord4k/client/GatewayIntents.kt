package ru.magmacube.discord4k.client

import net.dv8tion.jda.api.requests.GatewayIntent
import java.util.*

class GatewayIntents(intents: EnumSet<GatewayIntent> = EnumSet.noneOf(GatewayIntent::class.java)) {
    val intents: EnumSet<GatewayIntent>
    init {
        this.intents = intents
    }
    operator fun plusAssign(intent: GatewayIntent) {
        intents.add(intent)
    }
}