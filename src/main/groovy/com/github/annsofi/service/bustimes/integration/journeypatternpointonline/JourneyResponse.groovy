package com.github.annsofi.service.bustimes.integration.journeypatternpointonline

import com.fasterxml.jackson.annotation.JsonProperty

class JourneyResponse {
    @JsonProperty("StatusCode")
    int statusCode
    @JsonProperty("ExecutionTime")
    int executionTime
    @JsonProperty("ResponseData")
    JourneyResponseData responseData

    JourneyResponse() {
    }

    @Override
    String toString() {
        return "StopResponse{" +
                "statusCode=" + statusCode +
                ", executionTime=" + executionTime +
                ", responseData=" + responseData +
                '}';
    }
}
