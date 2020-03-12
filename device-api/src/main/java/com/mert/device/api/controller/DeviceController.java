package com.mert.device.api.controller;

import com.google.gson.Gson;
import com.mert.device.api.model.Device;
import com.mert.device.api.model.DeviceData;
import com.mert.device.api.service.impl.DeviceServiceImpl;
import com.mert.device.core.callback.KafkaMessageCallbackListener;
import com.mert.device.core.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/devices")
public class DeviceController {

    private final DeviceServiceImpl deviceService;
//    private final KafkaTemplate<String, Message> messageKafkaTemplate;
//    private KafkaTemplate<>

    @Autowired
    public DeviceController(DeviceServiceImpl deviceService) {
        this.deviceService = deviceService;
//        this.messageKafkaTemplate = messageKafkaTemplate;
    }

    @GetMapping("/{deviceSerialNumber}")
    public Optional<Device> getDevice(@PathVariable("deviceSerialNumber") UUID deviceSerialNumber) {
        return deviceService.getDevice(deviceSerialNumber);
    }

    @GetMapping("/")
    public List<Device> getDevices() {
        return deviceService.getDevices();
    }

    @PostMapping("/")
    public Device createDevice(@RequestBody Device device) {
        return deviceService.createDevice(device);
    }

    @PutMapping("/{deviceSerialNumber}")
    public void updateDevice(@RequestBody Device device, @PathVariable("deviceSerialNumber") UUID deviceSerialNumber) {
        deviceService.updateDevice(deviceSerialNumber, device);
    }

    @DeleteMapping("/{deviceSerialNumber}")
    public void deleteDevice(@PathVariable("deviceSerialNumber") UUID deviceSerialNumber) {
        deviceService.deleteDevice(deviceSerialNumber);
    }

    // DeviceData

    @PostMapping("/data")
    public void saveData(@RequestBody DeviceData deviceData) {
        deviceService.saveData(deviceData);
    }

    @GetMapping("/data")
    public List<DeviceData> getDevicesData() {
        return deviceService.getDevicesData();
    }



}
