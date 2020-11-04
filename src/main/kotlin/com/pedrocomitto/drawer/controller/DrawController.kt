package com.pedrocomitto.drawer.controller

import com.pedrocomitto.drawer.usecase.DrawUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/draw")
class DrawController(
    private val drawUseCase: DrawUseCase
) {

    @GetMapping
    fun draw() =
        drawUseCase.execute()

}
