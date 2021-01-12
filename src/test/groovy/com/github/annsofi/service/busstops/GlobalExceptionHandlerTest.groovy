package com.github.annsofi.service.busstops


import com.github.annsofi.service.busstops.integration.IntegrationException
import spock.lang.Specification

class GlobalExceptionHandlerTest extends Specification {
    GlobalExceptionHandler cut

    def setup() {
        cut = new GlobalExceptionHandler()
    }


    def "Should translate a integration exception to BAD_GATEWAY and preserve integration error code"() {
        given:
        Exception exception = new IntegrationException(1234)

        when:
        def serviceResponse = cut.handleException(exception)

        then:
        serviceResponse.body.error == '1234'
        serviceResponse.statusCodeValue == 502

    }

    def "Should translate a all other exceptions to INTERNAL_SERVER_ERROR"() {
        given:
        Exception exception = new NullPointerException()

        when:
        def serviceResponse = cut.handleException(exception)

        then:
        serviceResponse.body == null
        serviceResponse.statusCodeValue == 500

    }
}
