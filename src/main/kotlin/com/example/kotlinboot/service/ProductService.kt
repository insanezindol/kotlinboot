package com.example.kotlinboot.service

import com.example.kotlinboot.component.KafkaProducer
import com.example.kotlinboot.dto.ProductDto
import com.example.kotlinboot.dto.toResponse
import com.example.kotlinboot.entity.toEntity
import com.example.kotlinboot.repository.ProductRepository
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val repository: ProductRepository,
    private val producer: KafkaProducer
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun findAll(): List<ProductDto> {
        log.info("[service] find")
        return repository.findAll()
            .map {
                it.toResponse()
            }
    }

    @Cacheable(key = "#id", value = ["productFindById"])
    fun findById(
        id: Long
    ): ProductDto? {
        log.info("[service] findById : $id")
        val product = repository.findByIdOrNull(id)
            ?: throw NoSuchElementException("id $id element not found")
        return product
            .let {
                it.toResponse()
            }
    }

    @Cacheable(key = "#name", value = ["productFindByName"])
    fun findByName(
        name: String
    ): List<ProductDto> {
        log.info("[service] findByName")
        return repository.findByNameContainingIgnoreCase(name)
            .map {
                it.toResponse()
            }
    }

    fun add(
        product: ProductDto
    ): ProductDto? {
        log.info("[service] add : $product")
        return repository.save(product.toEntity()).toResponse()
    }

    fun addAsync(
        product: ProductDto
    ) {
        log.info("[service] addAsync : $product")
        producer.sendMessage(Gson().toJson(product))
    }

    @CacheEvict(key = "#id", value = ["productFindById"])
    fun modify(
        id: Long,
        product: ProductDto
    ): ProductDto? {
        log.info("[service] modify : $product")
        repository.findByIdOrNull(id)
            ?: throw NoSuchElementException("id $id element not found")
        return repository.save(product.toEntity(id)).toResponse()
    }

    @CacheEvict(key = "#id", value = ["productFindById"])
    fun remove(
        id: Long
    ) {
        log.info("[service] remove : $id")
        repository.findByIdOrNull(id)
            ?: throw NoSuchElementException("id $id element not found")
        repository.deleteById(id)
    }

}
