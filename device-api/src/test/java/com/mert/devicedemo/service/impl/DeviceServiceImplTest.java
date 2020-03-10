package com.mert.devicedemo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mert.device.api.exception.AlreadyCreatedModelException;
import com.mert.device.api.model.Device;
import com.mert.device.api.model.DeviceData;
import com.mert.device.api.repository.DeviceDataRepository;
import com.mert.device.api.repository.DeviceRepository;
import com.mert.device.api.service.DeviceService;
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
    private static final long dataCreatedAtTwo = 98724987349L;

    private Device deviceOne;
    private Device deviceTwo;
    private DeviceData deviceDataOne;
    //    private DeviceData deviceDataOneCopy;
    private DeviceData deviceDataTwo;

    @BeforeEach
    void setUp() {
        this.deviceOne = new Device(serialVersionUID, "deviceOne", "aliasDeviceOne", "", "");
        this.deviceTwo = new Device(serialVersionUIDTwo, "deviceTwo", "aliasDeviceTwo", "", "");
        this.deviceDataOne = new DeviceData(serialVersionUID, dataCreatedAt, null);
//        this.deviceDataOneCopy = new DeviceData(serialVersionUID, dataCreatedAt, null);
        this.deviceDataTwo = new DeviceData(serialVersionUIDTwo, dataCreatedAtTwo, null);
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
        // A device should be created with same id
        deviceService.createDevice(deviceOne);
        deviceService.saveData(deviceDataOne);
        Optional<DeviceData> testData = deviceDataRepository.findById(serialVersionUID);
        assertTrue(testData.isPresent());
    }

//
//    @Test
//    void saveData_deviceNotFound() {
//        deviceService.saveData(deviceDataOne);
//        Optional<DeviceData> testData = deviceDataRepository.findById(serialVersionUID);
//        assertFalse(testData.isPresent());
//    }

    @Test
    void saveData_dataAlreadyExists() {
        deviceRepository.save(deviceOne);
        deviceDataRepository.save(deviceDataOne);

//        deviceDataRepository.save(deviceDataOne);
//        deviceService.saveData(deviceDataOne);
        Exception exception = assertThrows(AlreadyCreatedModelException.class, () -> {
            deviceService.saveData(deviceDataOne);
        });

        String expectedMessage = "For exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getDevicesData() {
        deviceDataRepository.save(deviceDataOne);
        deviceDataRepository.save(deviceDataTwo);
        List<DeviceData> deviceDataList = deviceService.getDevicesData();
        assertEquals(2, deviceDataList.size());
    }
}