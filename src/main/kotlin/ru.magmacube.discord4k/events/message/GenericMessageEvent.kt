package ru.magmacube.discord4k.events.message

import ru.magmacube.discord4k.Client
import ru.magmacube.discord4k.events.GenericEvent

open class GenericMessageEvent(override val client: Client) : GenericEvent {
}