package com.github.annsofi.service.bustimes.api

class IntegrationException extends RuntimeException {
    int errorCode

    IntegrationException() {
    }

    IntegrationException(int errorCode) {
        super()
        this.errorCode = errorCode
    }

    @Override
    String toString() {
        return "IntegrationException{" +
                "errorCode=" + errorCode +
                '}'
    }
}
