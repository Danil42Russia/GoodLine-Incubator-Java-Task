package ru.danil42russia.pasta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PastaApplication

fun main(args: Array<String>) {
	runApplication<PastaApplication>(*args)
}
