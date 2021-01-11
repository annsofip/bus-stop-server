package com.github.annsofi.service.bustimes.api

class BusStopResponse {
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
