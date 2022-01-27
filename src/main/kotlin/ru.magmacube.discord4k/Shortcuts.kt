package ru.magmacube.discord4k

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import ru.magmacube.discord4k.event.CoroutineEventManager

val Client.scope: CoroutineScope get() = (eventManager as? CoroutineEventManager) ?: GlobalScope