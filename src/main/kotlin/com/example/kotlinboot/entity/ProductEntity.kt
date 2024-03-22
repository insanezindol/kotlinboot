package com.example.kotlinboot.entity

import com.example.kotlinboot.dto.ProductDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "products")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val code: String,
    val name: String,
    val price: Long
)

fun ProductDto.toEntity() = ProductEntity(
    code = code,
    name = name,
    price = price
)

fun ProductDto.toEntity(id: Long) = ProductEntity(
    id = id,
    code = code,
    name = name,
    price = price
)
