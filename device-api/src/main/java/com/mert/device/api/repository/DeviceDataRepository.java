package com.mert.device.api.repository;

import com.mert.device.api.model.Device;
import com.mert.device.api.model.DeviceData;
import com.mert.device.api.model.DeviceDataId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceDataRepository extends MongoRepository<DeviceData, UUID> {
}
