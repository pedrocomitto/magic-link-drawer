package com.pedrocomitto.drawer.controller

import com.pedrocomitto.drawer.service.DrawService
import com.pedrocomitto.drawer.service.impl.DrawServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/draw")
class DrawController(
    private val drawUseCase: DrawService
) {

    @GetMapping
    fun draw() =
        drawUseCase.draw()

}
