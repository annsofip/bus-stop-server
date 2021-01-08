package com.github.annsofi.service.bustimes.integration.stop

import com.fasterxml.jackson.annotation.JsonProperty

class StopResult {
    @JsonProperty("StopPointNumber")
    String stopPointNumber
    @JsonProperty("StopPointName")
    String stopPointName
    @JsonProperty("StopAreaNumber")
    String stopAreaNumber
    @JsonProperty("LocationNorthingCoordinate")
    String locationNorthingCoordinate
    @JsonProperty("LocationEastingCoordinate")
    String locationEastingCoordinate
    @JsonProperty("ZoneShortName")
    String zoneShortName
    @JsonProperty("StopAreaTypeCode")
    String stopAreaTypeCode
    @JsonProperty("LastModifiedUtcDateTime")
    String lastModifiedUtcDateTime
    @JsonProperty("ExistsFromDate")
    String existsFromDate

    StopResult() {
    }

    StopResult(String stopPointNumber, String stopPointName, String stopAreaNumber, String locationNorthingCoordinate, String locationEastingCoordinate, String zoneShortName, String stopAreaTypeCode, String lastModifiedUtcDateTime, String existsFromDate) {
        this.stopPointNumber = stopPointNumber
        this.stopPointName = stopPointName
        this.stopAreaNumber = stopAreaNumber
        this.locationNorthingCoordinate = locationNorthingCoordinate
        this.locationEastingCoordinate = locationEastingCoordinate
        this.zoneShortName = zoneShortName
        this.stopAreaTypeCode = stopAreaTypeCode
        this.lastModifiedUtcDateTime = lastModifiedUtcDateTime
        this.existsFromDate = existsFromDate
    }

    @Override
    String toString() {
        return "StopResult{" +
                "stopPointNumber='" + stopPointNumber + '\'' +
                ", stopPointName='" + stopPointName + '\'' +
                ", stopAreaNumber='" + stopAreaNumber + '\'' +
                ", locationNorthingCoordinate='" + locationNorthingCoordinate + '\'' +
                ", locationEastingCoordinate='" + locationEastingCoordinate + '\'' +
                ", zoneShortName='" + zoneShortName + '\'' +
                ", stopAreaTypeCode='" + stopAreaTypeCode + '\'' +
                ", lastModifiedUtcDateTime='" + lastModifiedUtcDateTime + '\'' +
                ", existsFromDate='" + existsFromDate + '\'' +
                '}'
    }
}
