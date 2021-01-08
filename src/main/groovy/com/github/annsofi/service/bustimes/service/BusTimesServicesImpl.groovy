package com.github.annsofi.service.bustimes.service

import com.github.annsofi.service.bustimes.api.BusLine
import com.github.annsofi.service.bustimes.api.BusStopResponse
import com.github.annsofi.service.bustimes.integration.journeypatternpointonline.*
import com.github.annsofi.service.bustimes.integration.stop.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class BusTimesServicesImpl implements BusTimesServices {
    @Autowired
    RestTemplate restTemplate

    final Logger log = LoggerFactory.getLogger(BusTimesServicesImpl.class)

    @Override
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/test")
    @ResponseBody
    BusStopResponse test() {
        HttpHeaders headers = new HttpHeaders()
        headers.setAccept([MediaType.APPLICATION_JSON])
        HttpEntity<String> entity = new HttpEntity<String>(headers)
        log.info("Calling Service")

        StopResponse stop = restTemplate.getForObject(
                "https://api.sl.se/api2/LineData.json?model=stop&key=1c10090f7fc046ad9dbe81895b82aaab&DefaultTransportModeCode=BUS", StopResponse.class)
        log.info("stop:")

        List<StopResult> stopResults = stop?.responseData?.results

        Map stopPointNumberToStopName = stopResults.collectEntries { [it.stopPointNumber, it.stopPointName] }


        JourneyResponse journeyPatternPointOnLine = restTemplate.getForObject(
                "https://api.sl.se/api2/LineData.json?model=jour&key=1c10090f7fc046ad9dbe81895b82aaab&DefaultTransportModeCode=BUS", JourneyResponse.class)
        log.info("journeyPatternPointOnLine:")
        log.info(journeyPatternPointOnLine.toString())

        List<JourneyResult> journeyResults = journeyPatternPointOnLine?.responseData?.results


        Map lineNumberToJourneyResult = journeyResults.inject([:].withDefault { [] }) { map, elem ->
            map[elem.lineNumber] << elem
            map
        }

        Map sorted = lineNumberToJourneyResult.sort { -it.value.size() }

        List<JourneyResult> top10 = sorted.entrySet().toList()[0..9]

        List<BusLine> busLines = top10.collect({ busLine ->
            List stops = busLine.value.collect { s ->
                [
                        "stopId"       : s.journeyPatternPointNumber,
                        "stopName"     : stopPointNumberToStopName.get(s.journeyPatternPointNumber),
                        "directionCode": s.directionCode
                ]
            }

            [
                    "lineNumber"   : busLine.key,
                    "stops"        : stops,
                    "numberOfStops": stops.size()
            ]
        })


        log.info(lineNumberToJourneyResult.toString())

        return new BusStopResponse([busLines: busLines])

    }
}
