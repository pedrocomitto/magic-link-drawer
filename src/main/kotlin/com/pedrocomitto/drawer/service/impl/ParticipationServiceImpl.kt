package com.pedrocomitto.drawer.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.exception.AlreadyParticipatingException
import com.pedrocomitto.drawer.notification.Notification
import com.pedrocomitto.drawer.producer.ParticipationProducer
import com.pedrocomitto.drawer.repository.ParticipationRepository
import com.pedrocomitto.drawer.service.ParticipationService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class ParticipationServiceImpl(
    private val participationRepository: ParticipationRepository,
    private val participationProducer: ParticipationProducer,
    private val cacheService: CacheServiceImpl,
    private val tokenService: TokenServiceImpl,
) : ParticipationService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun request(participation: Participation) {
        if (cacheService[participation.email] != null || participationRepository.existsById(participation.email)) {
            log.warn("M=request, I=this email is already participating, email=${participation.email}")
            throw AlreadyParticipatingException()
        }

        participationProducer.produce(participation)
    }

    override fun confirm(token: String) {
        val participation = tokenService.parseToken(token).let {
            jacksonObjectMapper().convertValue(it["participation"], Participation::class.java)
        }

        participationRepository.insert(participation)

        try {
            cacheService.set(participation.email, participation.name, Duration.ofMinutes(120))
        } catch (e: Throwable) {
            log.warn("M=confirm, I=error trying to set cache, email=${participation.email}")
        }
    }
}