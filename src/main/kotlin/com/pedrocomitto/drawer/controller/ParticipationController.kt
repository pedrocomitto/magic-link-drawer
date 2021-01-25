package com.pedrocomitto.drawer.controller

import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.service.ParticipationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/participations")
class ParticipationController(
    private val participationService: ParticipationService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun request(@RequestBody participation: Participation) {
        participationService.request(participation)
    }

    @GetMapping("/confirm")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun confirm(@RequestParam token: String) {
        participationService.confirm(token)
    }

}