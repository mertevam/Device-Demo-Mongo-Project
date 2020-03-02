package com.mert.devicedemo.service;

import com.mert.devicedemo.model.Device;
import com.mert.devicedemo.model.DeviceData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceService {

    List<Device> getDevices();

    // @NotNull can be added to String deviceSerialNumber
    Optional<Device> getDevice(UUID deviceSerialNumber);

    Device createDevice(Device device);

    void updateDevice(UUID deviceSerialNumber, Device device);

    // @NotNull can be added to String deviceSerialNumber
    void deleteDevice(UUID deviceSerialNumber);

    // Device Data
    void saveData(DeviceData deviceData);
}
