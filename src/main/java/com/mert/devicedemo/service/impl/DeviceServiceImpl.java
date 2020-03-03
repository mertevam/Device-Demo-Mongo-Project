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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
        if (deviceRepository.findById(deviceSerialNumber).isPresent()) {
            Device existingDevice = deviceRepository.findById(deviceSerialNumber).get();

            existingDevice.setDeviceName(device.getDeviceName());
            existingDevice.setAliasName(device.getAliasName());
            existingDevice.setCreatedAt(device.getCreatedAt());
            existingDevice.setUpdatedAt(device.getUpdatedAt());

            deviceRepository.save(existingDevice);
        }
        else {
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
        Optional<DeviceData> existingDeviceData = deviceDataRepository.findById(deviceData.getId());
        if (existingDevice.isPresent()) {
            if (!existingDeviceData.isPresent()) {
                log.debug("Device save successfully. Device data: {} ", deviceData);
                deviceDataRepository.save(deviceData);
            }
            else {
                throw new AlreadyCreatedModelException(DeviceData.class.getSimpleName(), deviceData.getId().toString());
            }
        }
        else {
            throw new NotFoundModelException(Device.class.getSimpleName(), deviceData.getId().toString());
            // System.out.println("Device can not found with this device data id!");
        }
    }

    @Override
    public List<DeviceData> getDevicesData() {
        return deviceDataRepository.findAll();
    }
}
