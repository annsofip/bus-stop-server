package com.github.annsofi.service.busstops.integration.stop

import com.fasterxml.jackson.annotation.JsonProperty

class StopResponse {
    @JsonProperty("StatusCode")
    int statusCode
    @JsonProperty("ExecutionTime")
    int executionTime
    @JsonProperty("ResponseData")
    StopResponseData responseData

    StopResponse() {
    }

    @Override
    String toString() {
        return "StopResponse{" +
                "statusCode=" + statusCode +
                ", executionTime=" + executionTime +
                ", responseData=" + responseData +
                '}'
    }
}
