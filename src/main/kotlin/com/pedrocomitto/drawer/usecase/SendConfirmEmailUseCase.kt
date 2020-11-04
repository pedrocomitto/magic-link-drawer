package com.pedrocomitto.drawer.usecase

import com.pedrocomitto.drawer.config.stereotype.UseCase
import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.service.EmailService
import com.pedrocomitto.drawer.service.TokenService
import org.slf4j.LoggerFactory

@UseCase
class SendConfirmEmailUseCase(
    private val emailService: EmailService,
    private val tokenService: TokenService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(participation: Participation) {
        tokenService.createToken(hashMapOf("participation" to participation))
                .let { emailService.send(it, participation.email) }

        log.info("email sent, email=${participation.email}")
    }

}
