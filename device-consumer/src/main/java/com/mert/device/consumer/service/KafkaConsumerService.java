package com.mert.device.consumer.service;

import com.mert.device.core.model.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;


public interface KafkaConsumerService {
    public void consume(ConsumerRecord<String, Message> message);
}
