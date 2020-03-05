package com.mert.devicedemo.repository;

import com.mert.devicedemo.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DeviceRepository extends MongoRepository<Device, UUID> {

}
