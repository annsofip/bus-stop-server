package com.github.annsofi.service.bustimes.api

class BusLine {
    String lineNumber
    List<Stop> stops
    int numberOfStops

    BusLine() {
    }

    @Override
    String toString() {
        return "BusLine{" +
                "lineNumber='" + lineNumber + '\'' +
                ", stops=" + stops +
                ", numberOfStops=" + numberOfStops +
                '}'
    }
}
