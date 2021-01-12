package com.github.annsofi.service.busstops.api

import io.swagger.annotations.ApiModelProperty

class BusStopResponse {
    @ApiModelProperty(value = "List of the bus lines with most stop, sorted from most to least", required = true)
    List<BusLine> busLines

    BusStopResponse() {
    }


    @Override
    String toString() {
        return "BusStopResponse{" +
                "busLines=" + busLines +
                '}'
    }
}
