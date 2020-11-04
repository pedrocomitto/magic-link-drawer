package com.pedrocomitto.drawer.usecase

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.pedrocomitto.drawer.config.stereotype.UseCase
import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.repository.ParticipationRepository
import com.pedrocomitto.drawer.service.CacheService
import com.pedrocomitto.drawer.service.TokenService
import java.time.Duration
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional

@UseCase
class ConfirmParticipationUseCase(
    private val participationRepository: ParticipationRepository,
    private val tokenService: TokenService,
    private val cacheService: CacheService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(token: String) {
        val participation = tokenService.parseToken(token).let {
            jacksonObjectMapper().convertValue(it["participation"], Participation::class.java)
        }

        participationRepository.insert(participation)

        try {
            cacheService.set(participation.email, participation.name, Duration.ofMinutes(120))
        } catch (e: Throwable) {
            log.warn("M=execute, I=error trying to set cache, email=${participation.email}")
        }
    }

}
