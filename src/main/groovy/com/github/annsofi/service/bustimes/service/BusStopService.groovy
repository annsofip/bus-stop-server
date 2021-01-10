package com.github.annsofi.service.bustimes.service

import com.github.annsofi.service.bustimes.api.BusLine
import com.github.annsofi.service.bustimes.api.Stop
import com.github.annsofi.service.bustimes.integration.JourneyIntegration
import com.github.annsofi.service.bustimes.integration.StopIntegration
import com.github.annsofi.service.bustimes.integration.journeypatternpointonline.JourneyResponse
import com.github.annsofi.service.bustimes.integration.journeypatternpointonline.JourneyResult
import com.github.annsofi.service.bustimes.integration.stop.StopResponse
import com.github.annsofi.service.bustimes.integration.stop.StopResult
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

    private static List<BusLine> translateBusLines(List<Map.Entry<String, List<JourneyResult>>> topResults, Map<String, StopResult> stopPointNumberToStopResult) {
        topResults.collect({ Map.Entry<String, List<JourneyResult>> busLine ->
            List<Stop> stops = busLine.value.collect { JourneyResult jr ->
                StopResult stopResult = stopPointNumberToStopResult.get(jr.journeyPatternPointNumber)
                if (stopResult == null) {
                    // This should not happen, I think but API doc is confusing so not sure
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

            new BusLine([
                    "lineNumber"   : busLine.value[0].lineNumber,
                    "stops"        : stops,
                    "numberOfStops": stops.size()
            ])
        })
    }

    private static List<Map.Entry<String, List<JourneyResult>>> getTopBusLines(List<JourneyResult> results, int numberOfLines) {
        Map<String, List<JourneyResult>> lineNumToJourneyResult =
                results.inject([:].withDefault { new ArrayList<JourneyResult>() }) { map, elem ->
                    map[elem.lineNumber] << elem
                    map
                }

        Map<String, List<JourneyResult>> sorted = lineNumToJourneyResult.sort { -it.value.size() }
        sorted.entrySet().toList()[0..numberOfLines - 1]
    }

}
