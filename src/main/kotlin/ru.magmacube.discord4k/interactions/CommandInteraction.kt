package ru.magmacube.discord4k.interactions

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.CommandInteraction
import ru.magmacube.discord4k.Client

class CommandInteraction internal constructor(val client: Client, val event: SlashCommandEvent) {
}