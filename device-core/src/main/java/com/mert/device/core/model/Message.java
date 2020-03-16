package com.mert.device.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
public class Message {

//    private String context;
//    private long timestamp;

    @Id
    private CompositeId id;
    private Map<String, BigDecimal> parameters;

    @JsonCreator
    public Message(@JsonProperty("message") CompositeId id,
                   @JsonProperty("parameters") Map<String, BigDecimal> parameters) {
        this.id = id;
        this.parameters = parameters;
    }


}
