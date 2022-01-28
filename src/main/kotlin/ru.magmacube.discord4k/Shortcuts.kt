package ru.magmacube.discord4k

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import ru.magmacube.discord4k.event.CoroutineEventManager
import kotlin.time.Duration

fun JDABuilder.injectD4k(timeout: Duration = Duration.INFINITE) = setEventManager(CoroutineEventManager(timeout = timeout))
fun DefaultShardManagerBuilder.injectD4k(timeout: Duration = Duration.INFINITE) = setEventManagerProvider { CoroutineEventManager(timeout = timeout) }