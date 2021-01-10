package com.github.annsofi.service.bustimes.api

import com.fasterxml.jackson.annotation.JsonFormat

import java.time.LocalDateTime

class ServiceError {

    private  List<String> errors
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp
    private String message
    private String debugMessage

    private ServiceError() {
        timestamp = LocalDateTime.now()
    }

    ServiceError(List<String> errors) {
        this()
        this.errors = errors
    }

    ServiceError(List<String> errors, Throwable ex) {
        this()
        this.errors = errors
        this.message = "Unexpected error"
        this.debugMessage = ex.getLocalizedMessage()
    }

    ServiceError(List<String> errors, String message, Throwable ex) {
        this()
        this.errors = errors
        this.message = message
        this.debugMessage = ex.getLocalizedMessage()
    }
}
