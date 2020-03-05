package com.mert.device.api.repository;

import com.mert.device.api.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DeviceRepository extends MongoRepository<Device, UUID> {

}
