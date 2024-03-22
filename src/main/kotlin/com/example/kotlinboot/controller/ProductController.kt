package com.example.kotlinboot.controller

import com.example.kotlinboot.dto.ProductDto
import com.example.kotlinboot.dto.ResponseDto
import com.example.kotlinboot.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@Tag(name = "상품관리", description = "상품 조회, 등록, 수정, 삭제 API")
@RestController
@RequestMapping("/product")
class ProductController(
    private val productService: ProductService
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Operation(summary = "전체 상품 조회", description = "전체 상품 목록을 조회합니다.")
    @GetMapping("")
    fun findAll(): ResponseEntity<ResponseDto<List<ProductDto>>> {
        log.info("[controller] findAll")
        return ResponseEntity.ok().body(
            ResponseDto.success(productService.findAll())
        )
    }

    @Operation(summary = "상품 ID 조회", description = "상품 ID로 조회합니다.")
    @GetMapping("/{id}")
    fun findById(
        @Parameter(
            name = "id",
            description = "상품 ID",
            required = true,
            example = "1"
        )
        @PathVariable id: Long
    ): ResponseEntity<ResponseDto<ProductDto?>> {
        log.info("[controller] findById")
        return ResponseEntity.ok().body(
            ResponseDto.success(productService.findById(id))
        )
    }

    @Operation(summary = "상품 이름 조회", description = "상품 이름으로 조회합니다.")
    @GetMapping("/name")
    fun findByName(
        @Parameter(
            name = "name",
            description = "상품 이름",
            required = true,
            example = "product name"
        )
        @RequestParam(required = true) name: String
    ): ResponseEntity<ResponseDto<List<ProductDto>>> {
        log.info("[controller] findByName")
        return ResponseEntity.ok().body(
            ResponseDto.success(productService.findByName(name))
        )
    }

    @Operation(summary = "상품 등록", description = "상품을 등록합니다.")
    @PostMapping("", consumes = ["application/json"])
    fun add(
        @RequestBody product: ProductDto
    ): ResponseEntity<ResponseDto<ProductDto?>> {
        log.info("[controller] add")
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseDto.success(productService.add(product))
        )
    }

    @Operation(summary = "상품 async 등록", description = "상품을 비동기로 등록합니다.")
    @PostMapping("/async", consumes = ["application/json"])
    fun addAsync(
        @RequestBody product: ProductDto
    ): ResponseEntity<Unit> {
        log.info("[controller] addAsync")
        productService.addAsync(product)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @Operation(summary = "상품 수정", description = "ID에 해당되는 상품을 수정합니다.")
    @PutMapping("/{id}", consumes = ["application/json"])
    fun modify(
        @Parameter(
            name = "id",
            description = "상품 ID",
            required = true,
            example = "1"
        )
        @PathVariable id: Long,
        @RequestBody product: ProductDto
    ): ResponseEntity<ResponseDto<ProductDto?>> {
        log.info("[controller] modify")
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseDto.success(productService.modify(id, product))
        )
    }

    @Operation(summary = "상품 삭제", description = "ID에 해당되는 상품을 삭제합니다.")
    @DeleteMapping("/{id}")
    fun remove(
        @Parameter(
            name = "id",
            description = "상품 ID",
            required = true,
            example = "1"
        )
        @PathVariable id: Long
    ): ResponseEntity<Unit> {
        log.info("[controller] remove")
        productService.remove(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

}
