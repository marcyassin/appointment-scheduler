package com.yassin.appointments.dao

import com.yassin.appointments.models.Appointment
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * Dao class from demonstration purposes. We could use a NoSQL store
 */
@Repository
class AppointmentDao {
    final var appointments = mutableListOf<Appointment>()

    private final val clientAId = "cId1234"
    private final val clientAStart: LocalDateTime = LocalDateTime.of(2021, 3, 19, 12, 0, 0)

    init {
        val a1 = Appointment(clientAId, clientAStart, clientAStart.plusMinutes(30))
        appointments.add(a1)
    }

    fun insertAppointment(newAppointment: Appointment): Appointment {
        appointments.add(newAppointment)
        return newAppointment
    }

    fun findAppointmentsByClientId(clientId: String): List<Appointment> {
        return appointments.filter { it.clientId == clientId }
    }
}