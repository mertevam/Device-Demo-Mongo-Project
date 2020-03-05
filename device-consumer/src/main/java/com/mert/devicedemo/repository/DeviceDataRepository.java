package com.mert.devicedemo.repository;

import com.mert.devicedemo.model.Device;
import com.mert.devicedemo.model.DeviceData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DeviceDataRepository extends MongoRepository<DeviceData, UUID> {
}
