package com.mert.device.core.service;

import com.mert.device.core.model.Message;

public interface KafkaProducerService {
    void sendMessage(Message message);
//    void sendLog(String log);
}
