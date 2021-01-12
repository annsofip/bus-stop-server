package com.github.annsofi.service.busstops.api

import com.sun.istack.internal.NotNull
import io.swagger.annotations.ApiModelProperty

class Stop {
    @ApiModelProperty(value = "Unique id of the stop", required = true)
    String stopId
    @ApiModelProperty(value = "Name of the stop", required = true)
    String stopName
    @ApiModelProperty(value = "Direction of the stop on the line, either 1 or 2", required = true)
    String directionCode
    @ApiModelProperty(value = "Id of the stop area the stop belongs to, multiple stop in the same area belongs to the same stop area", required = true)
    String stopAreaNumber
    @ApiModelProperty(value = "Zone to which the stop belongs", required = true)
    @NotNull
    String zoneShortName


    Stop() {
    }

    @Override
    String toString() {
        return "Stop{" +
                "stopId='" + stopId + '\'' +
                ", stopName='" + stopName + '\'' +
                ", directionCode='" + directionCode + '\'' +
                ", stopAreaNumber='" + stopAreaNumber + '\'' +
                ", zoneShortName='" + zoneShortName + '\'' +
                '}'
    }
}
