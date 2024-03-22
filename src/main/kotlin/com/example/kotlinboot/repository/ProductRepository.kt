package com.example.kotlinboot.repository

import com.example.kotlinboot.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, Long> {

    fun findByNameContainingIgnoreCase(name: String): Iterable<ProductEntity>

}
