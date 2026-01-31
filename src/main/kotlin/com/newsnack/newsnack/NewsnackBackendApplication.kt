package com.newsnack.newsnack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class NewsnackBackendApplication

fun main(args: Array<String>) {
	runApplication<NewsnackBackendApplication>(*args)
}
