package com.github.annsofi.service.bustimes.api

class IntegrationException extends RuntimeException{
    IntegrationException() {
    }

    IntegrationException(String var1) {
        super(var1)
    }

    IntegrationException(String var1, Throwable var2) {
        super(var1, var2)
    }

    IntegrationException(Throwable var1) {
        super(var1)
    }

    IntegrationException(String var1, Throwable var2, boolean var3, boolean var4) {
        super(var1, var2, var3, var4)
    }
}
