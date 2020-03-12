package com.mert.device.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
public class DeviceDataTest {

//    private String context;
//    private long timestamp;

    private UUID id;
    private long timestamp;
    private Map<String, BigDecimal> parameters;

    @JsonCreator
    public DeviceDataTest(@JsonProperty("message") UUID id,
                   @JsonProperty("timestamp") long timestamp,
                   @JsonProperty("parameters") Map<String, BigDecimal> parameters) {
        this.id = id;
        this.timestamp = timestamp;
        this.parameters = parameters;
    }
}
