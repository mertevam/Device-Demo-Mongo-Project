package com.mert.device.api.service.impl;

import com.google.gson.Gson;
import com.mert.device.api.exception.AlreadyCreatedModelException;
import com.mert.device.api.exception.NotFoundModelException;
import com.mert.device.api.model.Device;
import com.mert.device.api.model.DeviceData;
import com.mert.device.api.repository.DeviceDataRepository;
import com.mert.device.api.repository.DeviceRepository;
import com.mert.device.api.service.DeviceService;
import com.mert.device.core.callback.KafkaMessageCallbackListener;
import com.mert.device.core.model.CompositeId;
import com.mert.device.core.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private final KafkaTemplate<String, Message> messageKafkaTemplate;
    private final KafkaMessageCallbackListener messageCallbackListener;

    private final DeviceRepository deviceRepository;
    private final DeviceDataRepository deviceDataRepository;
//    private final MessageRepository messageRepository;

    @Value(value = "${kafka.topic.message.topicName}")
    private String messageTopicName;

    @Autowired
    public DeviceServiceImpl(KafkaTemplate<String, Message> messageKafkaTemplate, DeviceRepository deviceRepository, DeviceDataRepository deviceDataRepository) {
        this.messageKafkaTemplate = messageKafkaTemplate;
        this.messageCallbackListener = new KafkaMessageCallbackListener();
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

        deviceDataRepository.save(deviceData);
        log.debug("Device save successfully. Device data: {} ", deviceData);
    }

    @Override
    public void saveData(Message message) {
        CompositeId id = new CompositeId(UUID.randomUUID(), 213123123123L);
        Message testMessage = new Message(id, null);
//        testMessage.setId(UUID.randomUUID());
//        testMessage.setTimestamp(123123213213213L);
        log.info("Message is sending...");
        ListenableFuture<SendResult<String, Message>> messageTopic = messageKafkaTemplate.send(messageTopicName, testMessage);
        messageTopic.addCallback(messageCallbackListener);
        log.info("Message has sent!");

    }

//    @Override
//    public Optional<Message> getData(CompositeId id) {
//        return
//    }

    @Override
    public List<DeviceData> getDevicesData() {
        return deviceDataRepository.findAll();
    }
}
