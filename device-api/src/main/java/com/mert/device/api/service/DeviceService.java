package com.mert.device.api.service;

import com.mert.device.api.model.Device;
import com.mert.device.api.model.DeviceData;
import com.mert.device.core.model.CompositeId;
import com.mert.device.core.model.Message;

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

    void saveData(Message message);

//    Optional<Message> getData(CompositeId id);

    List<DeviceData> getDevicesData();
}
