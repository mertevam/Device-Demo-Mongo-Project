package com.mert.devicedemo.controller;

import com.mert.devicedemo.model.Device;
import com.mert.devicedemo.model.DeviceData;
import com.mert.devicedemo.service.impl.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/devices")
public class DeviceController {

    private final DeviceServiceImpl deviceService;

    @Autowired
    public DeviceController(DeviceServiceImpl deviceService) {
        this.deviceService = deviceService;
    }


    @GetMapping("/{device-serial-number}")
    public Optional<Device> getDevice(@PathVariable("device-serial-number") UUID deviceSerialNumber) {
        return deviceService.getDevice(deviceSerialNumber);
    }

    @GetMapping("/")
    public List<Device> getDevice() {
        return deviceService.getDevices();
    }

    @PostMapping("/")
    public Device createDevice(@RequestBody Device device) {
        return deviceService.createDevice(device);
    }

    @PutMapping("/{device-serial-number}")
    public void updateDevice(@RequestBody Device device, @PathVariable("device-serial-number") UUID deviceSerialNumber) {
        deviceService.updateDevice(deviceSerialNumber ,device);
    }

    @DeleteMapping("/{device-serial-number}")
    public void deleteDevice(@PathVariable("device-serial-number") UUID deviceSerialNumber){
        deviceService.deleteDevice(deviceSerialNumber);
    }

    // DeviceData

    @PostMapping("/data")
    public void saveData(@RequestBody DeviceData deviceData){
        deviceService.saveData(deviceData);
    }

}
