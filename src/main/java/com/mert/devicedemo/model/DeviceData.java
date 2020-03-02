package com.mert.devicedemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data    // contains Getter, Setter, and toString
@AllArgsConstructor
@NoArgsConstructor
public class DeviceData {

    @Id
    private UUID id;
    private long createdAt;            // could get by currenttimemilis()
    private Map<String, BigDecimal> parameters;

}
