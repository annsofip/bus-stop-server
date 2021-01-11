package com.github.annsofi.service.bustimes.integration

import com.github.annsofi.service.bustimes.api.IntegrationException
import com.github.annsofi.service.bustimes.integration.journeypatternpointonline.JourneyResponse
import com.github.annsofi.service.bustimes.integration.journeypatternpointonline.JourneyResponseData
import com.github.annsofi.service.bustimes.integration.journeypatternpointonline.JourneyResult
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class JourneyIntegrationTest extends Specification {

    JourneyIntegration cut
    RestTemplate restTemplate

    String integrationEndpoint

    String apiKey

    def setup() {
        cut = new JourneyIntegration()
        restTemplate = Stub(RestTemplate.class)
        integrationEndpoint = 'integrationEndpoint'
        apiKey = 'apiKey'

        cut.restTemplate = restTemplate
        cut.integrationEndpoint = integrationEndpoint
        cut.apiKey = apiKey
    }


    def "Should throw Exception with errorCode when responseData is missing"() {
        given:
        JourneyResponse journeyResponse = new JourneyResponse()
        journeyResponse.statusCode = 100

        and:
        restTemplate.getForObject("${integrationEndpoint}&key=${apiKey}", JourneyResponse.class) >> journeyResponse

        when:
         cut.get()

        then:
        final IntegrationException exception = thrown()
        exception.errorCode == 100
    }

    def "Should not throw Exception with errorCode when responseData exist"() {
        given:
        JourneyResponse journeyResponse = new JourneyResponse()
        journeyResponse.statusCode = 100
        journeyResponse.responseData = new JourneyResponseData()

        and:
        restTemplate.getForObject("${integrationEndpoint}&key=${apiKey}", JourneyResponse.class) >> journeyResponse

        when:
         cut.get()

        then:
        final IntegrationException exception = notThrown()
    }
}
