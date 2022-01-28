package ru.magmacube.discord4k.entities

import net.dv8tion.jda.api.entities.User

interface User : Snowflake {
    val jdaUser: User
}