package com.github.annsofi.service.bustimes.integration.journeypatternpointonline

import com.fasterxml.jackson.annotation.JsonProperty

class JourneyResponseData {
    @JsonProperty("Version")
    String version
    @JsonProperty("Type")
    String type
    @JsonProperty("Result")
    List<JourneyResult> results

     JourneyResponseData() {
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
