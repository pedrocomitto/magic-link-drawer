package com.pedrocomitto.drawer.controller

import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.usecase.ConfirmParticipationUseCase
import com.pedrocomitto.drawer.usecase.RequestParticipationUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/participation")
class ParticipationController(
    private val requestParticipationUseCase: RequestParticipationUseCase,
    private val confirmParticipationUseCase: ConfirmParticipationUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun requestParticipation(@RequestBody participation: Participation) {
        requestParticipationUseCase.execute(participation)
    }

    @GetMapping("/confirm")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun confirmParticipation(@RequestParam token: String) {
        confirmParticipationUseCase.execute(token)
    }

}