package com.github.annsofi.service.busstops.api

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty

import java.time.LocalDateTime

class ServiceError {

    @JsonProperty("error")
    @ApiModelProperty(value = "Integration service error code", required = true)
    private  String error
    @ApiModelProperty(value = "timestamp for the response", required = true)
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
