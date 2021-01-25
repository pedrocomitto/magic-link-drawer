package com.pedrocomitto.drawer.consumer

import com.pedrocomitto.drawer.config.RabbitConfig
import com.pedrocomitto.drawer.config.stereotype.Consumer
import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.notification.Notification
import com.pedrocomitto.drawer.notification.NotificationSender
import com.pedrocomitto.drawer.service.TokenService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener

@Consumer
class ParticipationConsumer(
    private val notificationSender: NotificationSender,
    private val tokenService: TokenService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = [RabbitConfig.QUEUE_NAME])
    fun consume(participation: Participation) {
        log.info("M=consume, I=consuming message, email=${participation.email}")

        val notification = Notification.of(participation)

        tokenService.createToken(hashMapOf("participation" to participation))
            .let { notificationSender.send(it, notification) }
    }

}