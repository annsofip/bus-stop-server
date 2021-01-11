package com.github.annsofi.service.bustimes.integration

import com.github.annsofi.service.bustimes.api.IntegrationException
import com.github.annsofi.service.bustimes.integration.journeypatternpointonline.JourneyResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    @Value('${se.sl.api.apiKey}')
    String apiKey

    final Logger log = LoggerFactory.getLogger(JourneyIntegration.class)

    @Cacheable(value="journeyResponse")
    JourneyResponse get() {
        log.debug("Calling ${integrationEndpoint}")
        JourneyResponse response = (JourneyResponse) restTemplate.getForObject("${integrationEndpoint}&key=${apiKey}", JourneyResponse.class)
        log.debug("Response  ${response}")

        if (response == null) {
            throw new IntegrationException()
        } else if (response.responseData == null) {
            throw new IntegrationException(response.statusCode)
        }

        return response
    }

}
