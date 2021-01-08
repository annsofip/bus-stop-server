package com.github.annsofi.service.bustimes


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class Application {


    static void main(String[] args) {

        SpringApplication.run(Application, args)
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build()
    }

}
