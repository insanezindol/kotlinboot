package com.example.kotlinboot.dto

import com.example.kotlinboot.entity.ProductEntity
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "상품 객체")
data class ProductDto(
    @Schema(
        description = "상품 아이디",
        example = "1",
        type = "long"
    )
    val id: Long,
    @Schema(
        description = "상품 코드",
        example = "PRD-001",
        type = "string"
    )
    val code: String,
    @Schema(
        description = "상품 이름",
        example = "this is product name",
        type = "string"
    )
    val name: String,
    @Schema(
        description = "상품 가격",
        example = "10000",
        type = "long"
    )
    val price: Long
) {
    constructor() : this(0, "", "", 0)
}

fun ProductEntity.toResponse() = ProductDto(
    id = id,
    code = code,
    name = name,
    price = price
)
