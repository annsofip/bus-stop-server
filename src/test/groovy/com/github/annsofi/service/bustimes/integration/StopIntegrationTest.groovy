package com.github.annsofi.service.bustimes.integration

import com.github.annsofi.service.bustimes.api.IntegrationException
import com.github.annsofi.service.bustimes.integration.stop.StopResponse
import com.github.annsofi.service.bustimes.integration.stop.StopResponseData
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class StopIntegrationTest extends Specification {
    StopIntegration cut
    RestTemplate restTemplate

    String integrationEndpoint

    String apiKey

    def setup() {
        cut = new StopIntegration()
        restTemplate = Stub(RestTemplate.class)
        integrationEndpoint = 'integrationEndpoint'
        apiKey = 'apiKey'

        cut.restTemplate = restTemplate
        cut.integrationEndpoint = integrationEndpoint
        cut.apiKey = apiKey
    }


    def "Should throw Exception with errorCode when responseData is missing"() {
        given:
        StopResponse stopResponse = new StopResponse()
        stopResponse.statusCode = 100

        and:
        restTemplate.getForObject("${integrationEndpoint}&key=${apiKey}", StopResponse.class) >> stopResponse

        when:
        cut.get()

        then:
        final IntegrationException exception = thrown()
        exception.errorCode == 100
    }

    def "Should not throw Exception with errorCode when responseData exist"() {
        given:
        StopResponse stopResponse = new StopResponse()
        stopResponse.statusCode = 100
        stopResponse.responseData = new StopResponseData()

        and:
        restTemplate.getForObject("${integrationEndpoint}&key=${apiKey}", StopResponse.class) >> stopResponse

        when:
        cut.get()

        then:
        final IntegrationException exception = notThrown()
    }
}
