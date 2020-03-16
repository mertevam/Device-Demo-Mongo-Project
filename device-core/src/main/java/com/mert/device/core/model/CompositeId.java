package com.mert.device.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositeId implements Serializable{

    private UUID serialNumber;
    private long timestamp;
}
