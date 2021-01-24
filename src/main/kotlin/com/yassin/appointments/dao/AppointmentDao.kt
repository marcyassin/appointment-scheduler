package com.yassin.appointments.dao

import com.yassin.appointments.models.Appointment
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * Dao class from demonstration purposes. We could use a NoSQL store
 */
@Repository
class AppointmentDao {
    final var allAppointments = mutableListOf<Appointment>()

    val clientAId = "marc"
    val clientAStart: LocalDateTime = LocalDateTime.of(2021, 3, 19, 12, 0, 0)

    init {
        val a1 = Appointment(clientAId, clientAStart, clientAStart.plusMinutes(30))
        allAppointments.add(a1)
    }

    fun insertAppointment(newAppointment: Appointment): Appointment {
        allAppointments.add(newAppointment)
        return newAppointment
    }

    fun findAppointmentsByClientId(clientId: String): List<Appointment> {
        return allAppointments.filter { it.clientId == clientId }
    }
}