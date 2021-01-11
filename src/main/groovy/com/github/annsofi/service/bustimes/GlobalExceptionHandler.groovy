package com.github.annsofi.service.bustimes

import com.github.annsofi.service.bustimes.api.ServiceError
import com.github.annsofi.service.bustimes.api.IntegrationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler([IntegrationException.class])
    final ResponseEntity<ServiceError> handleException(Exception ex) {
        HttpHeaders headers = new HttpHeaders()

        if (ex instanceof IntegrationException) {
            HttpStatus status = HttpStatus.NOT_FOUND
            IntegrationException unfe = (IntegrationException) ex
            List<String> errors = Collections.singletonList(unfe.getMessage())
            return handleExceptionInternal(new ServiceError(errors), headers, status)
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR
            return handleExceptionInternal(null, headers, status)
        }
    }

    static ResponseEntity<ServiceError> handleExceptionInternal(ServiceError body, HttpHeaders headers, HttpStatus status) {

        return new ResponseEntity<ServiceError>(body, headers, status)
    }
}
