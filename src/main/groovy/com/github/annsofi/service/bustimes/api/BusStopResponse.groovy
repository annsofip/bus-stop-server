package com.github.annsofi.service.bustimes.api

class BusStopResponse {
    List<BusLine> busLines

    BusStopResponse(BusLine busLines) {
        this.busLines = busLines
    }

    BusStopResponse() {
    }
}
