package com.pedrocomitto.drawer.service

import com.pedrocomitto.drawer.document.Participation

interface ParticipationService {

    fun request(participation: Participation)

    fun confirm(token: String)
}