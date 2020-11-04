package com.pedrocomitto.drawer.usecase

import com.pedrocomitto.drawer.exception.AlreadyParticipatingException
import com.pedrocomitto.drawer.config.stereotype.UseCase
import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.producer.ParticipationProducer
import com.pedrocomitto.drawer.repository.ParticipationRepository
import com.pedrocomitto.drawer.service.CacheService
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import java.time.Duration

@UseCase
class RequestParticipationUseCase(
    private val participationRepository: ParticipationRepository,
    private val participationProducer: ParticipationProducer,
    private val cacheService: CacheService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(participation: Participation) {
        if (cacheService[participation.email] != null || participationRepository.existsById(participation.email)) {
            log.error("This email is already participating")
            throw AlreadyParticipatingException()
        }

        participationProducer.produce(participation)
    }

}