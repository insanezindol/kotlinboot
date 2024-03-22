package com.example.kotlinboot.component

import com.example.kotlinboot.config.TOPIC_NAME
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun sendMessage(message: String) {
        log.info("Message sended: [$message]")
        kafkaTemplate.send(TOPIC_NAME, message)
    }

}
