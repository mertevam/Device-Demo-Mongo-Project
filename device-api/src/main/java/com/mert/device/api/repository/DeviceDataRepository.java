package com.mert.device.api.repository;

import com.mert.device.api.model.Device;
import com.mert.device.api.model.DeviceData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DeviceDataRepository extends MongoRepository<DeviceData, UUID> {
}
