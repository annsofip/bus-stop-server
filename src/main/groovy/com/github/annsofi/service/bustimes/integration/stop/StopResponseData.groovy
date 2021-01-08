package com.github.annsofi.service.bustimes.integration.stop

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.annsofi.service.bustimes.integration.journeypatternpointonline.JourneyResult

class StopResponseData {
    @JsonProperty("Version")
    String version
    @JsonProperty("Type")
    String type
    @JsonProperty("Result")
    List<StopResult> results

     StopResponseData() {
    }

    @Override
     String toString() {
        return "StopResponseData{" +
                "version='" + version + '\'' +
                ", type='" + type + '\'' +
                ", results=" + results +
                '}'
    }
}
