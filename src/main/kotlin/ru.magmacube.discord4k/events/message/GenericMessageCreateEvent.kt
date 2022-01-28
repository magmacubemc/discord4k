package ru.magmacube.discord4k.events.message

import ru.magmacube.discord4k.Client
import ru.magmacube.discord4k.events.GenericEvent

open class GenericMessageCreateEvent(override val client: Client) : GenericMessageEvent(client) {
}