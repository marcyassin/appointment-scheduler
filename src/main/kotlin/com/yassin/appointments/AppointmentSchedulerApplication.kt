package com.yassin.appointments

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppointmentSchedulerApiApplication

fun main(args: Array<String>) {
	runApplication<AppointmentSchedulerApiApplication>(*args)
}
