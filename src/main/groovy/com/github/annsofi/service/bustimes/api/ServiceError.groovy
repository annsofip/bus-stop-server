package com.github.annsofi.service.bustimes.api

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus

import java.time.LocalDateTime

class ServiceError {

    private HttpStatus status
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp
    private String message
    private String debugMessage

    private ServiceError() {
        timestamp = LocalDateTime.now()
    }

    ServiceError(HttpStatus status) {
        this()
        this.status = status
    }

    ServiceError(HttpStatus status, Throwable ex) {
        this()
        this.status = status
        this.message = "Unexpected error"
        this.debugMessage = ex.getLocalizedMessage()
    }

    ServiceError(HttpStatus status, String message, Throwable ex) {
        this()
        this.status = status
        this.message = message
        this.debugMessage = ex.getLocalizedMessage()
    }
}
