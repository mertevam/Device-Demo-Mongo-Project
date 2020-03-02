package com.mert.devicedemo.service.impl;

import com.mert.devicedemo.model.Device;
import com.mert.devicedemo.repository.DeviceRepository;
import com.mert.devicedemo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
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
        Device existingDevice = deviceRepository.findById(deviceSerialNumber).get();

        existingDevice.setDeviceName(device.getDeviceName());
        existingDevice.setAliasName(device.getAliasName());
        existingDevice.setCreatedAt(device.getCreatedAt());
        existingDevice.setUpdatedAt(device.getUpdatedAt());

        deviceRepository.save(existingDevice);
    }

    @Override
    public void deleteDevice(UUID deviceSerialNumber) {
        Device existingDevice = deviceRepository.findById(deviceSerialNumber).get();
        deviceRepository.delete(existingDevice);
    }
}
