package com.mert.devicedemo.service.impl;

import com.mert.devicedemo.exception.AlreadyCreatedModelException;
import com.mert.devicedemo.exception.NotFoundModelException;
import com.mert.devicedemo.model.Device;
import com.mert.devicedemo.model.DeviceData;
import com.mert.devicedemo.repository.DeviceDataRepository;
import com.mert.devicedemo.repository.DeviceRepository;
import com.mert.devicedemo.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceDataRepository deviceDataRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceDataRepository deviceDataRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceDataRepository = deviceDataRepository;
    }

    @Override
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Optional<Device> getDevice(UUID deviceSerialNumber) {
        return deviceRepository.findById(deviceSerialNumber);
    }

    @Override
    public Device createDevice(Device device) {

        return deviceRepository.save(device);
    }

    @Override
    public void updateDevice(UUID deviceSerialNumber, Device device) {
        Optional<Device> optionalDevice = deviceRepository.findById(deviceSerialNumber);
        if (optionalDevice.isPresent()) {
            Device existingDevice = optionalDevice.get();

            existingDevice.setDeviceName(device.getDeviceName());
            existingDevice.setAliasName(device.getAliasName());
            existingDevice.setCreatedAt(device.getCreatedAt());
            existingDevice.setUpdatedAt(device.getUpdatedAt());

            deviceRepository.save(existingDevice);
        } else {
            throw new NotFoundModelException(Device.class.getSimpleName(), device.getSerialNumber().toString());
        }
    }

    @Override
    public void deleteDevice(UUID deviceSerialNumber) {
        Device existingDevice = deviceRepository.findById(deviceSerialNumber).get();  // isPresent should be added!
        deviceRepository.delete(existingDevice);
    }

    // DeviceData

    @Override
    public void saveData(DeviceData deviceData) {
        Optional<Device> existingDevice = deviceRepository.findById(deviceData.getId());
        String id = deviceData.getId().toString();
        if (!existingDevice.isPresent()) {
            throw new NotFoundModelException(Device.class.getSimpleName(), id);
        }

        Optional<DeviceData> existingDeviceData = deviceDataRepository.findById(deviceData.getId());
        if (existingDeviceData.isPresent()) {
            throw new AlreadyCreatedModelException(DeviceData.class.getSimpleName(), id);
        }

        log.debug("Device save successfully. Device data: {} ", deviceData);
        deviceDataRepository.save(deviceData);
    }

    @Override
    public List<DeviceData> getDevicesData() {
        return deviceDataRepository.findAll();
    }
}
