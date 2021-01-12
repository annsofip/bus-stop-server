package com.github.annsofi.service.busstops.api

import io.swagger.annotations.ApiModelProperty

class BusLine {
    @ApiModelProperty(value = "The number of the bus line", required = true)
    String lineNumber
    @ApiModelProperty(value = "List of all the stops on the line", required = true)
    List<Stop> stops
    @ApiModelProperty(value = "Total number of stops on the line", required = true)
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
