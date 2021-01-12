package com.github.annsofi.service.busstops.service


import com.github.annsofi.service.busstops.api.Stop
import com.github.annsofi.service.busstops.integration.JourneyIntegration
import com.github.annsofi.service.busstops.integration.StopIntegration
import com.github.annsofi.service.busstops.integration.journeypatternpointonline.JourneyResponse
import com.github.annsofi.service.busstops.integration.journeypatternpointonline.JourneyResponseData
import com.github.annsofi.service.busstops.integration.journeypatternpointonline.JourneyResult
import com.github.annsofi.service.busstops.integration.stop.StopResponse
import com.github.annsofi.service.busstops.integration.stop.StopResponseData
import com.github.annsofi.service.busstops.integration.stop.StopResult
import spock.lang.Specification

class BusStopServiceTest extends Specification {

    JourneyIntegration journeyIntegration

    StopIntegration stopIntegration
    BusStopService cut

    def setup() {
        cut = new BusStopService()
        journeyIntegration = Stub(JourneyIntegration.class)
        stopIntegration = Stub(StopIntegration.class)

        cut.journeyIntegration = journeyIntegration
        cut.stopIntegration = stopIntegration
    }


    def "should return the bus line with most stops"() {
        given: "Three bus stops and a bus line with three stops and a stop with one stop"
        StopResponse stopResponse = new StopResponse()
        stopResponse.responseData = new StopResponseData()
        stopResponse.responseData.results = [new StopResult(['stopPointNumber': '1']),
                                             new StopResult(['stopPointNumber': '2']),
                                             new StopResult(['stopPointNumber': '3'])]

        JourneyResponse journeyResponse = new JourneyResponse()
        journeyResponse.responseData = new JourneyResponseData()
        journeyResponse.responseData.results = [new JourneyResult(['lineNumber'               : '641',
                                                                   'journeyPatternPointNumber': '1']),
                                                new JourneyResult(['lineNumber'               : '636',
                                                                   'journeyPatternPointNumber': '1']),
                                                new JourneyResult(['lineNumber'               : '636',
                                                                   'journeyPatternPointNumber': '2']),
                                                new JourneyResult(['lineNumber'               : '636',
                                                                   'journeyPatternPointNumber': '3'])]


        and: "stop integration service returns three stops and journey integration service return these two bus lines"
        stopIntegration.get() >> stopResponse
        journeyIntegration.get() >> journeyResponse

        when: "requesting the line with most stops"
        def busLineWithMaxStops = cut.getBusLineWithMaxStops(1)

        then: "the line with three stops is returned"
        busLineWithMaxStops.get(0).lineNumber == '636'
    }

    def "Should translate bus stop correctly"() {
        given: "a bus stop and a mapping of bus stop to names"
        Map<String, StopResult> stopPointNumberToStopResult = ['1': new StopResult(['stopPointNumber': '1',
                                                                                    'stopPointName'  : 'Tekniska högskolan'])]

        JourneyResult journeyResult = new JourneyResult(['lineNumber'               : '636',
                                                         'journeyPatternPointNumber': '1'])


        when: "translating the stop"
        def busStop = cut.translateStop(stopPointNumberToStopResult, journeyResult)

        then: "the stop is translated correctly"
        busStop.stopName == 'Tekniska högskolan'
    }

    def "Should translate bus line correctly"() {
        given: "a bus line"
        Map<String, StopResult> stopPointNumberToStopResult = ['1': new StopResult(['stopPointNumber': '1',
                                                                                    'stopPointName'  : 'Tekniska högskolan'])]

        JourneyResult journeyResult = new JourneyResult(['lineNumber'               : '636',
                                                         'journeyPatternPointNumber': '1'])
        Map<String, List<JourneyResult>> busLines = ['636': [journeyResult]]

        Stop stop = new Stop()

        and: "the stop is translated correctly"
        cut.translateStop(stopPointNumberToStopResult, journeyResult) >> stop

        when: "translating the bus line"
        def translatedBusLines = cut.translateBusLines(busLines, stopPointNumberToStopResult)

        then: "the stop is translated correctly"
        translatedBusLines[0].lineNumber == '636'
    }

    def "Should select the chosen number of top bus lines"() {
        given: "a list of journey results"

        List<JourneyResult> journeyResult = [new JourneyResult(['lineNumber'               : '636',
                                                                'journeyPatternPointNumber': '1']),
                                             new JourneyResult(['lineNumber'               : '637',
                                                                'journeyPatternPointNumber': '1']),
                                             new JourneyResult(['lineNumber'               : '640',
                                                                'journeyPatternPointNumber': '1']),
                                             new JourneyResult(['lineNumber'               : '641',
                                                                'journeyPatternPointNumber': '1']),
                                             new JourneyResult(['lineNumber'               : '645',
                                                                'journeyPatternPointNumber': '1']),
                                             new JourneyResult(['lineNumber'               : '636',
                                                                'journeyPatternPointNumber': '2']),
                                             new JourneyResult(['lineNumber'               : '637',
                                                                'journeyPatternPointNumber': '2']),
                                             new JourneyResult(['lineNumber'               : '640',
                                                                'journeyPatternPointNumber': '2']),
                                             new JourneyResult(['lineNumber'               : '641',
                                                                'journeyPatternPointNumber': '2']),
                                             new JourneyResult(['lineNumber'               : '645',
                                                                'journeyPatternPointNumber': '2']),
                                             new JourneyResult(['lineNumber'               : '650',
                                                                'journeyPatternPointNumber': '1'])
        ]



        when: "getting the 3 bus lines with the most stops"
        def topBusLines = cut.getTopBusLines(journeyResult, 3)

        then: "then 3 bus lines are returned"
        topBusLines.size() == 3
    }


}
