package com.pedrocomitto.drawer.controller.advice

import com.pedrocomitto.drawer.response.ApiExceptionResponse
import com.pedrocomitto.drawer.exception.AlreadyParticipatingException
import com.pedrocomitto.drawer.exception.ExpiredTokenException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(AlreadyParticipatingException::class)
    fun handleAlreadyParticipatingException(exception: Exception) =
            ResponseEntity.status(HttpStatus.CONFLICT).body(ApiExceptionResponse(exception.message))

    @ExceptionHandler(ExpiredTokenException::class)
    fun handleExpiredTokenException(exception: Exception) =
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ApiExceptionResponse(exception.message))
}
