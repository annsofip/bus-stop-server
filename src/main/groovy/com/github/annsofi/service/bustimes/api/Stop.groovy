package com.github.annsofi.service.bustimes.api

class Stop {
    String stopId
    String stopName
    String directionCode
    String stopAreaNumber
    String zoneShortName


    Stop() {
    }

    @Override
    String toString() {
        return "Stop{" +
                "stopId='" + stopId + '\'' +
                ", stopName='" + stopName + '\'' +
                ", directionCode='" + directionCode + '\'' +
                ", stopAreaNumber='" + stopAreaNumber + '\'' +
                ", zoneShortName='" + zoneShortName + '\'' +
                '}'
    }
}
