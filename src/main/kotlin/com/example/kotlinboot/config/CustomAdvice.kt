package com.example.kotlinboot.config

import com.example.kotlinboot.dto.ResponseDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class CustomAdvice {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(RuntimeException::class)
    fun handle400Exception(e: Throwable): ResponseEntity<ResponseDto<String>> {
        log.error("" + e)
        val errorResponse = ResponseDto(
            code = -1,
            message = "Bad Request",
            data = e.message
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(
        NoSuchElementException::class,
        NoHandlerFoundException::class,
        NoResourceFoundException::class
    )
    fun handle404Exception(e: Throwable): ResponseEntity<ResponseDto<String>> {
        log.error("" + e)
        val errorResponse = ResponseDto(
            code = -1,
            message = "Not Found",
            data = e.message
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handle405Exception(e: Throwable): ResponseEntity<ResponseDto<String>> {
        log.error("" + e)
        val errorResponse = ResponseDto(
            code = -1,
            message = "Method Not Allowed",
            data = e.message
        )
        return ResponseEntity(errorResponse, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(Exception::class)
    fun handle500Exception(e: Exception): ResponseEntity<ResponseDto<String>> {
        log.error("" + e)
        val errorResponse = ResponseDto(
            code = -1,
            message = "Internal Server Error",
            data = e.message
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
