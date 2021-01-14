package com.github.annsofi.service.busstops.integration

import com.github.annsofi.service.busstops.integration.journeypatternpointonline.JourneyResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class JourneyIntegration {

    @Autowired
    RestTemplate restTemplate


    @Value('${service.journeyPatternPointOnLineEndpoint}')
    String integrationEndpoint


    String apiKey = System.getenv('TRAFIKLAB_API_KEY')


    @Cacheable(value="journeyResponse")
    JourneyResponse get() {
        JourneyResponse response = (JourneyResponse) restTemplate.getForObject("${integrationEndpoint}&key=${apiKey}", JourneyResponse.class)

        if (response == null) {
            throw new IntegrationException()
        } else if (response.responseData == null) {
            throw new IntegrationException(response.statusCode)
        }

        return response
    }

}
