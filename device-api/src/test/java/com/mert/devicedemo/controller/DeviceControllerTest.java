package com.mert.devicedemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mert.devicedemo.model.Device;
import com.mert.devicedemo.model.DeviceData;
import com.mert.devicedemo.repository.DeviceDataRepository;
import com.mert.devicedemo.repository.DeviceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DeviceControllerTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

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
    void setUp() throws JsonProcessingException {
        this.deviceOne = new Device(serialVersionUID, "deviceOne", "aliasDeviceOne", "", "");
        this.deviceTwo = new Device(serialVersionUIDTwo, "deviceTwo", "aliasDeviceTwo", "", "");
        this.deviceDataOne = new DeviceData(serialVersionUID, dataCreatedAt, null);
        this.deviceStr = objectMapper.writeValueAsString(deviceOne);
        this.deviceStrTwo = objectMapper.writeValueAsString(deviceTwo);

    }

    @AfterEach
    void tearDown() {
        deviceRepository.deleteAll();
        deviceDataRepository.deleteAll();
    }

    @Test
    void getDevice() throws Exception {
        this.deviceRepository.save(deviceOne);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/devices/" + serialVersionUID)
                .content(deviceStr)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceName").value(deviceOne.getDeviceName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serialNumber").exists());
    }

    @Test
    void getDevices() throws Exception {
        this.deviceRepository.save(deviceOne);
        this.deviceRepository.save(deviceTwo);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/devices/")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].serialNumber").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].aliasName").exists());
    }

    @Test
    void createDevice() {
    }

    @Test
    void updateDevice() {
    }

    @Test
    void deleteDevice() {
    }

    @Test
    void saveData() {
    }

    @Test
    void getDevicesData() {
        this.deviceDataRepository.save(deviceDataOne);
    }
}