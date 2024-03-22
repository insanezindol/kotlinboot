package com.example.kotlinboot.component

import com.example.kotlinboot.config.GROUP_ID
import com.example.kotlinboot.config.TOPIC_NAME
import com.example.kotlinboot.dto.ProductDto
import com.example.kotlinboot.service.ProductService
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
    private val productService: ProductService
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(topics = [TOPIC_NAME], groupId = GROUP_ID)
    fun receiveMessage(message: String) {
        log.info("Message received: [$message]")
        val product = Gson().fromJson(message, ProductDto::class.java)
        productService.add(product)
    }

}
