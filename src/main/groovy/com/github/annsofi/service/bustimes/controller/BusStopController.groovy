package com.github.annsofi.service.bustimes.controller

import com.github.annsofi.service.bustimes.api.BusLine
import com.github.annsofi.service.bustimes.api.BusStopResponse
import com.github.annsofi.service.bustimes.service.BusStopService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BusStopController {


    @Autowired
    BusStopService busStopService

    final Logger log = LoggerFactory.getLogger(BusStopController.class)

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/topstops")
    @ResponseBody
    ResponseEntity<BusStopResponse> test() {
        HttpHeaders headers = new HttpHeaders()
        headers.setAccept([MediaType.APPLICATION_JSON])
        log.info('Call to topStops endpoint')
        List<BusLine> busLines = busStopService.getBusLineWithMaxStops(10)
        return new ResponseEntity<>(new BusStopResponse([busLines: busLines]), headers, HttpStatus.OK)
    }

}
