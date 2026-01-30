package com.newsnack.newsnack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewsnackBackendApplication

fun main(args: Array<String>) {
	runApplication<NewsnackBackendApplication>(*args)
}
