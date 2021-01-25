package com.pedrocomitto.drawer.producer

import com.pedrocomitto.drawer.config.RabbitConfig
import com.pedrocomitto.drawer.config.stereotype.Producer
import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.notification.Notification
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate

@Producer
class ParticipationProducer(
    private val rabbitTemplate: RabbitTemplate
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun produce(participation: Participation) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.QUEUE_ROUTING_KEY, participation)

        log.info("M=produce, I=message sent, email=${participation.email}")
    }

}
