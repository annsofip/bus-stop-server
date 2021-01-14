package com.github.annsofi.service.busstops.controller

import com.github.annsofi.service.busstops.Constant
import com.github.annsofi.service.busstops.api.BusLine
import com.github.annsofi.service.busstops.api.BusStopResponse
import com.github.annsofi.service.busstops.api.ServiceError
import com.github.annsofi.service.busstops.service.BusStopService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(Constant.BUS_LINES_MOST_STOPS)
@RequestMapping(value = Constant.BUS_LINES_MOST_STOPS)
class BusStopController {

    @Autowired
    BusStopService busStopService

    final Logger log = LoggerFactory.getLogger(BusStopController.class)

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get the bus lines with the most bus stops",
            notes = "Retrieving a list of bus stops, the number of bus lines is determined by lineCount in the request",
            response = BusStopResponse.class)
    @ApiResponses([
            @ApiResponse(code = 200, message = "Success", response = BusStopResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ServiceError.class),
            @ApiResponse(code = 502, message = "integration error", response = ServiceError.class)
    ])
    @ResponseBody
    ResponseEntity<BusStopResponse> topStops(@RequestParam(required = false, defaultValue = '10')  int lineCount) {
        HttpHeaders headers = new HttpHeaders()
        headers.setAccept([MediaType.APPLICATION_JSON])
        log.info('Call to topStops endpoint')
        List<BusLine> busLines = busStopService.getBusLineWithMaxStops(lineCount)
        log.info('Return response to client')
        return new ResponseEntity<BusStopResponse>(new BusStopResponse([busLines: busLines]), headers, HttpStatus.OK)
    }

}
