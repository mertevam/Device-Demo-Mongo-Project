package com.mert.device.consumer.service;

import com.mert.device.core.model.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;


public interface KafkaConsumerService {
//    public void consume(ConsumerRecord<String, Message> message);
    public void consumeFromApi(ConsumerRecord<String, Message> message);
    public List<Message> getMessages();
}
