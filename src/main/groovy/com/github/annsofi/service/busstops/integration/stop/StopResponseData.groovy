package com.github.annsofi.service.busstops.integration.stop

import com.fasterxml.jackson.annotation.JsonProperty

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
