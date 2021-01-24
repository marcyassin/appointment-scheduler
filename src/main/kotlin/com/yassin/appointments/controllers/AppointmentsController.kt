package com.yassin.appointments.controllers

import com.yassin.appointments.models.Appointment
import com.yassin.appointments.services.AppointmentService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping(value = ["/api/v1/appointment-scheduler/appointments"])
class AppointmentsController constructor(
        private val appointmentService: AppointmentService
){
    @GetMapping(value = ["/test"])
    fun test(): String = "hello"

    @GetMapping(value = ["/{clientId}"])
    fun getAppointments(
            @PathVariable clientId: String
    ): List<Appointment> = appointmentService.getAppointmentsByClientId(clientId)

    @PostMapping(value = ["/{clientId}"])
    fun createAppointment(
            @PathVariable clientId: String,
            @RequestParam start: String
    ): List<Appointment> {
        appointmentService.createAppointment(clientId, LocalDateTime.parse(start))
        return appointmentService.getAppointmentsByClientId(clientId)
    }
}