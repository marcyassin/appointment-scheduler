package com.yassin.appointments.models

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class Appointment(
        val clientId: String,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        val start: LocalDateTime,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        val end: LocalDateTime
)