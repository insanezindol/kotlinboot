package com.example.kotlinboot.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "응답 객체")
class ResponseDto<T>(
    @field:Schema(
        description = "응답 코드",
        example = "0",
        type = "int",
        minimum = "-1",
        maximum = "0"
    )
    val code: Int,
    @field:Schema(
        description = "응답 메시지",
        example = "success",
        type = "string"
    )
    val message: String?,
    @field:Schema(description = "응답 데이터")
    val data: T?,
) {
    companion object {
        fun <T> success(data: T): ResponseDto<T> {
            return ResponseDto(0, "success", data)
        }
    }
}
