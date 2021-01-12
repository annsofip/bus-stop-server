package com.github.annsofi.service.busstops.service

import com.github.annsofi.service.busstops.api.BusLine
import com.github.annsofi.service.busstops.api.Stop
import com.github.annsofi.service.busstops.integration.JourneyIntegration
import com.github.annsofi.service.busstops.integration.StopIntegration
import com.github.annsofi.service.busstops.integration.journeypatternpointonline.JourneyResponse
import com.github.annsofi.service.busstops.integration.journeypatternpointonline.JourneyResult
import com.github.annsofi.service.busstops.integration.stop.StopResponse
import com.github.annsofi.service.busstops.integration.stop.StopResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class BusStopService {

    @Autowired
    RestTemplate restTemplate

    @Autowired
    JourneyIntegration journeyIntegration

    @Autowired
    StopIntegration stopIntegration

    List<BusLine> getBusLineWithMaxStops(int lineCount) {
        StopResponse stop = stopIntegration.get()
        JourneyResponse journeyPatternPointOnLine = journeyIntegration.get()

        Map<String, StopResult> stopPointNumberToStopResult = stop.responseData.results.collectEntries {
            [it.stopPointNumber, it]
        }

        return translateBusLines(getTopBusLines(journeyPatternPointOnLine.responseData.results, lineCount), stopPointNumberToStopResult)
    }

    static List<BusLine> translateBusLines(Map<String, List<JourneyResult>> busLines, Map<String, StopResult> stopPointNumberToStopResult) {
        busLines.collect({ Map.Entry<String, List<JourneyResult>> busLine ->
            List<Stop> stops = busLine.value.collect { translateStop(stopPointNumberToStopResult, it) }

            new BusLine([
                    "lineNumber"   : busLine.value[0].lineNumber,
                    "stops"        : stops,
                    "numberOfStops": stops.size()
            ])
        })
    }

    static Stop translateStop(Map<String, StopResult> stopPointNumberToStopResult, JourneyResult jr) {
        StopResult stopResult = stopPointNumberToStopResult.get(jr.journeyPatternPointNumber)
        if (stopResult == null) {
            // This should not happen, I think but the API doc is confusing so not sure
            return new Stop([
                    "stopId"       : jr.journeyPatternPointNumber,
                    "directionCode": jr.directionCode
            ])
        } else {
            return new Stop([
                    "stopId"        : jr.journeyPatternPointNumber,
                    "directionCode" : jr.directionCode,
                    "stopAreaNumber": stopResult.stopAreaNumber,
                    "zoneShortName" : stopResult.zoneShortName,
                    "stopName"      : stopResult.stopPointName
            ])
        }
    }

    static Map<String, List<JourneyResult>> getTopBusLines(List<JourneyResult> results, int lineCount) {
        results.inject([:].withDefault { new ArrayList<JourneyResult>() }) { map, elem ->
            map[elem.lineNumber] << elem
            return map
        }.sort { -it.value.size() }
                .entrySet().toList()[0..lineCount - 1]
                .collectEntries { [(it.key): it.value] }
    }

}
