package com.mert.devicedemo.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Device implements Serializable {
    private static final long serialVersionUID = -5032040851554106477L;
    @Id
    private UUID serialNumber;
    private String deviceName;
    private String aliasName;
    private String createdAt;
    //    @ToString.Exclude
    private String updatedAt;

}
