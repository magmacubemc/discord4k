package ru.magmacube.discord4k.entities

import java.time.LocalDateTime

interface Message : Snowflake {
    val author: User
    val createdTime: LocalDateTime
}