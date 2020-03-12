package com.mert.device.api.service.impl;

import com.google.gson.Gson;
import com.mert.device.api.service.KafkaMessageService;
import com.mert.device.core.model.DeviceDataTest;
import com.mert.device.core.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageServiceImpl implements KafkaMessageService {

    private Gson jsonConverter;
    private KafkaTemplate<String, Message> messageKafkaTemplate;

    @KafkaListener(
            id = "23423423sdfdsfsd1",
            topics = {"${kafka.topic.data.topicName}"},
            groupId = "${kafka.topic.data.groupName}",
            containerFactory = "messageKafkaListenerContainerFactory",
            autoStartup = "true")
    @Override
    public void consume(ConsumerRecord<String, DeviceDataTest> deviceDataTestConsumerRecord) {
        System.out.println(deviceDataTestConsumerRecord);
        log.info("{}", deviceDataTestConsumerRecord);
    }

    @KafkaListener(id = "23sdd1",
            topics = "mert3",
            groupId = "MESSAGE_DATA",
            containerFactory = "messageKafkaListenerContainerFactory",
            autoStartup = "true")
    public void getFromKafka(Message messageFromKafka){
        System.out.println(messageFromKafka.toString());
        Message simpleMessage = jsonConverter.fromJson(String.valueOf(messageFromKafka), Message.class);
        System.out.println(simpleMessage.toString());
        log.info("received kafka message: {}", simpleMessage.toString());
//        messageKafkaTemplate.send("mert2", simpleMessage);

    }
}
