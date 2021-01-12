package com.github.annsofi.service.busstops.integration

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
