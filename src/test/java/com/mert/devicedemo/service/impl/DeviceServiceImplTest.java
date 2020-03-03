package com.mert.devicedemo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mert.devicedemo.model.Device;
import com.mert.devicedemo.model.DeviceData;
import com.mert.devicedemo.repository.DeviceDataRepository;
import com.mert.devicedemo.repository.DeviceRepository;
import com.mert.devicedemo.service.DeviceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DeviceServiceImplTest {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    private static final UUID serialVersionUID = UUID.randomUUID();
    private static final UUID serialVersionUIDTwo = UUID.randomUUID();
    //    private static final long dataSerialId = 12312343328L;
    private static final long dataCreatedAt = 12312343328L;

    private Device deviceOne;
    private Device deviceTwo;
    private DeviceData deviceDataOne;

    private String deviceStr;
    private String deviceStrTwo;


    @BeforeEach
    void setUp() {
        this.deviceOne = new Device(serialVersionUID, "deviceOne", "aliasDeviceOne", "", "");
        this.deviceTwo = new Device(serialVersionUIDTwo, "deviceTwo", "aliasDeviceTwo", "", "");
        this.deviceDataOne = new DeviceData(serialVersionUID, dataCreatedAt, null);
    }

    @AfterEach
    void tearDown() {
        deviceRepository.deleteAll();
        deviceDataRepository.deleteAll();
    }

    @Test
    void getDevices() {
        deviceRepository.save(deviceOne);
        deviceRepository.save(deviceTwo);
        List<Device> deviceList = deviceService.getDevices();
        assertEquals(2, deviceList.size());
    }

    @Test
    void getDevice() {
        deviceRepository.save(deviceOne);
        Optional<Device> testDevice = deviceService.getDevice(serialVersionUID);
        assertTrue(testDevice.isPresent());
    }

    @Test
    void createDevice() {
        deviceService.createDevice(deviceOne);
        Optional<Device> testDevice = deviceRepository.findById(serialVersionUID);
        assertTrue(testDevice.isPresent());
    }

    @Test
    void updateDevice() {
        deviceService.createDevice(deviceOne);
        deviceService.updateDevice(serialVersionUID, deviceTwo);
        Device updatedDevice = deviceRepository.findById(deviceOne.getSerialNumber()).get();
        assertEquals(deviceOne.getSerialNumber(), updatedDevice.getSerialNumber());
//        assertNotEquals(deviceTwo, updatedDevice);
        assertEquals(deviceTwo.getAliasName(), updatedDevice.getAliasName());
        assertEquals(deviceTwo.getDeviceName(), updatedDevice.getDeviceName());
        assertEquals(deviceTwo.getCreatedAt(), updatedDevice.getCreatedAt());
        assertEquals(deviceTwo.getUpdatedAt(), updatedDevice.getUpdatedAt());
    }

    @Test
    void deleteDevice() {
        deviceRepository.save(deviceOne);
        Optional<Device> testDevice = deviceService.getDevice(serialVersionUID);
        assertTrue(testDevice.isPresent());

        deviceService.deleteDevice(serialVersionUID);
        Optional<Device> testDevice2 = deviceRepository.findById(serialVersionUID);
        assertFalse(testDevice2.isPresent());
    }

    @Test
    void saveData() {
    }

    @Test
    void getDevicesData() {
    }
}