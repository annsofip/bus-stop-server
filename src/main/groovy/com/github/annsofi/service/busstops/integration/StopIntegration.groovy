package com.github.annsofi.service.busstops.integration


import com.github.annsofi.service.busstops.integration.stop.StopResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class StopIntegration {

    @Autowired
    RestTemplate restTemplate

    @Value('${service.busStopEndpoint}')
    String integrationEndpoint

    @Value('${se.sl.api.apiKey}')
    String apiKey

    final Logger log = LoggerFactory.getLogger(StopIntegration.class)

    @Cacheable(value="stopResponse")
    StopResponse get() {
        StopResponse response = (StopResponse) restTemplate.getForObject("${integrationEndpoint}&key=${apiKey}", StopResponse.class)

        if (response == null) {
            throw new IntegrationException()
        } else if (response.responseData == null) {
            throw new IntegrationException(response.statusCode)
        }

        return response
    }

}
