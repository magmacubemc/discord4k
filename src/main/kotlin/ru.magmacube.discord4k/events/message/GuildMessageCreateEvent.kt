package ru.magmacube.discord4k.events.message

import ru.magmacube.discord4k.Client
import ru.magmacube.discord4k.events.message.GenericMessageCreateEvent

class GuildMessageCreateEvent(override val client: Client) : GenericMessageCreateEvent(client) {
}