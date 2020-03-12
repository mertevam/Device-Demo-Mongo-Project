package com.mert.device.core.service;

import com.mert.device.core.model.DeviceDataTest;
import com.mert.device.core.model.Message;

public interface KafkaProducerService {
    void sendMessage(Message message);
    void sendDeviceDataTest(DeviceDataTest deviceDataTest);
}
