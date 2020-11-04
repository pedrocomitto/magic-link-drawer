package com.pedrocomitto.drawer.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    companion object {
        const val EXCHANGE_NAME = "participation.exchange"
        const val QUEUE_NAME = "participation.queue"
        const val QUEUE_ROUTING_KEY = QUEUE_NAME
        const val DLQ_NAME = "participation.queue.dlq"
        const val DLQ_ROUTING_KEY = DLQ_NAME
    }

    @Bean
    fun participationExchange() = ExchangeBuilder.topicExchange(EXCHANGE_NAME).build<TopicExchange>()

    @Bean
    fun participationQueue() =
        QueueBuilder.durable(QUEUE_NAME)
                .deadLetterExchange(EXCHANGE_NAME)
                .deadLetterRoutingKey(DLQ_ROUTING_KEY)
                .build()

    @Bean
    fun participationDlq() = QueueBuilder.durable(DLQ_NAME).build()

    @Bean
    fun participationQueueBinding(participationExchange: TopicExchange, participationQueue: Queue) =
            BindingBuilder
                    .bind(participationQueue)
                    .to(participationExchange)
                    .with(QUEUE_ROUTING_KEY)

    @Bean
    fun participationDlqBinding(participationExchange: TopicExchange, participationDlq: Queue) =
            BindingBuilder
                    .bind(participationDlq)
                    .to(participationExchange)
                    .with(DLQ_ROUTING_KEY)

    @Bean
    fun messageConverter(objectMapper: ObjectMapper) = Jackson2JsonMessageConverter(objectMapper)

}