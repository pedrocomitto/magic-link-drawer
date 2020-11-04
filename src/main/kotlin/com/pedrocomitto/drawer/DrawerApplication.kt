package com.pedrocomitto.drawer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class DrawerApplication

fun main(args: Array<String>) {
	runApplication<DrawerApplication>(*args)
}
