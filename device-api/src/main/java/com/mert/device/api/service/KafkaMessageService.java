package com.mert.device.api.service;

import com.mert.device.core.model.DeviceDataTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public interface KafkaMessageService {

    @KafkaListener(
            id = "23423423sdfdsfsd1",
            topics = {"${kafka.topic.data.topicName}"},
            groupId = "${kafka.topic.data.groupName}",
            containerFactory = "messageKafkaListenerContainerFactory",
            autoStartup = "true")
    void consume(ConsumerRecord<String, DeviceDataTest> deviceDataTestConsumerRecord);
}
