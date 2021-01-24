package com.yassin.appointments.services

import com.yassin.appointments.dao.AppointmentDao
import com.yassin.appointments.exceptions.BadRequestException
import com.yassin.appointments.models.Appointment
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AppointmentService constructor(
        private val appointmentDao: AppointmentDao
) {

    fun createAppointment(clientId: String, start: LocalDateTime): Appointment {
        if (!isValidStartTime(start)) {
            throw BadRequestException("Invalid Start Time. Appointments must start on the the hour or half hour.")
        }
        val end = start.plusMinutes(APPOINTMENT_DURATION)
        if (isAvailable(clientId, start, end)) {
            val appointment = Appointment(clientId, start, end)
            return appointmentDao.insertAppointment(appointment)
        } else {
            throw BadRequestException("Client already has appointment for this date.")
        }
    }

    fun getAppointmentsByClientId(clientId: String): List<Appointment> {
        return appointmentDao.findAppointmentsByClientId(clientId)
    }

    fun isAvailable(clientId: String, start: LocalDateTime, end: LocalDateTime): Boolean {
        val clientAppointments = getAppointmentsByClientId(clientId)
        val hasAppointmentOnThisDay = clientAppointments.stream().anyMatch {
            it.start.toLocalDate().isEqual(start.toLocalDate())
        }
        return !hasAppointmentOnThisDay
    }

    fun isValidStartTime(start: LocalDateTime): Boolean {
        return start.minute == 0 || start.minute == 30
    }

    companion object {
        const val APPOINTMENT_DURATION = 30L
    }
}