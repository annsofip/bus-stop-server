package com.github.annsofi.service.bustimes.api

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty

import java.time.LocalDateTime

class ServiceError {

    @JsonProperty("error")
    private  String error
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp
    private String message
    private String debugMessage

    private ServiceError() {
        this.timestamp = LocalDateTime.now()
    }

    ServiceError(String error) {
        this()
        this.error = error
    }

    ServiceError(String error, Throwable ex) {
        this()
        this.error = error
        this.message = "Unexpected error"
        this.debugMessage = ex.getLocalizedMessage()
    }

    ServiceError(String error, String message, Throwable ex) {
        this()
        this.error = error
        this.message = message
        this.debugMessage = ex.getLocalizedMessage()
    }
}
