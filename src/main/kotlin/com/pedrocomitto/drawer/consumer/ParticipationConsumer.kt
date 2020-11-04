package com.pedrocomitto.drawer.consumer

import com.pedrocomitto.drawer.config.RabbitConfig
import com.pedrocomitto.drawer.config.stereotype.Consumer
import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.usecase.SendConfirmEmailUseCase
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener

@Consumer
class ParticipationConsumer(
    private val sendConfirmEmailUseCase: SendConfirmEmailUseCase
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = [RabbitConfig.QUEUE_NAME])
    fun consume(participation: Participation) {
        log.info("consuming message, email=${participation.email}")

        sendConfirmEmailUseCase.execute(participation)
    }

}