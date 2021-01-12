package com.github.annsofi.service.busstops

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig {

    @Value('${client.url}')
    String clientUrl

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
             void addCorsMappings(CorsRegistry registry) {
                registry.addMapping('/api/**')
                        .allowedOrigins(clientUrl)
                        .allowedMethods("GET")
            }
        }
    }
}
