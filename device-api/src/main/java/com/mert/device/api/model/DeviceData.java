package com.mert.device.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

@Data    // contains Getter, Setter, and toString
@AllArgsConstructor
@NoArgsConstructor
public class DeviceData {

    @Id
    private UUID id;
    private long timestamp;            // String type da oluşturulabilir. get by System.currentTimeMillis() ,  Timestamp type da oluşturulabilir
    private Map<String, BigDecimal> parameters;

}
