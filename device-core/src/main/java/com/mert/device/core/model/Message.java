package com.mert.device.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {

    private String context;
    private long timestamp;

    @JsonCreator
    public Message(@JsonProperty("message") String context,
                   @JsonProperty("timestamp") long timestamp) {
        this.context = context;
        this.timestamp = timestamp;
    }
}
