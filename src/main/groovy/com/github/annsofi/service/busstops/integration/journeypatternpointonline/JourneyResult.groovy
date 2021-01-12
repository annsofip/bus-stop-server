package com.github.annsofi.service.busstops.integration.journeypatternpointonline

import com.fasterxml.jackson.annotation.JsonProperty

class JourneyResult {
    @JsonProperty("LineNumber")
    String lineNumber
    @JsonProperty("DirectionCode")
    String directionCode
    @JsonProperty("JourneyPatternPointNumber")
    String journeyPatternPointNumber
    @JsonProperty("LastModifiedUtcDateTime")
    String lastModifiedUtcDateTime
    @JsonProperty("ExistsFromDate")
    String existsFromDate

     JourneyResult() {
    }

     JourneyResult(String lineNumber, String directionCode, String journeyPatternPointNumber, String lastModifiedUtcDateTime, String existsFromDate) {
        this.lineNumber = lineNumber
        this.directionCode = directionCode
        this.journeyPatternPointNumber = journeyPatternPointNumber
        this.lastModifiedUtcDateTime = lastModifiedUtcDateTime
        this.existsFromDate = existsFromDate
    }

    @Override
     String toString() {
        return "StopResult{" +
                "lineNumber='" + lineNumber + '\'' +
                ", directionCode='" + directionCode + '\'' +
                ", journeyPatternPointNumber='" + journeyPatternPointNumber + '\'' +
                ", lastModifiedUtcDateTime='" + lastModifiedUtcDateTime + '\'' +
                ", existsFromDate='" + existsFromDate + '\'' +
                '}'
    }
}
