package com.mert.device.consumer.service.impl;

import com.mert.device.consumer.repository.MessageRepository;
import com.mert.device.consumer.service.KafkaConsumerService;
import com.mert.device.core.model.Message;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

//    @KafkaListener(
//            id = "23423423sdfdsfsd1",
//            topics = {"${kafka.topic.message.topicName}"},
//            groupId = "${kafka.topic.message.groupName}",
//            containerFactory = "messageKafkaListenerContainerFactory",
//            autoStartup = "true")
//    @Override
//    public void consume(ConsumerRecord<String, Message> message) {
//        System.out.println(message);
//        log.info("{}", message);
//
//    }

    private final MessageRepository messageRepository;

    @Autowired
    public KafkaConsumerServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @KafkaListener(
            id = "123",
            topics = {"${kafka.topic.message.topicName2}"},
            groupId = "${kafka.topic.message.groupName2}",
            containerFactory = "messageKafkaListenerContainerFactory",
            autoStartup = "true")
    @Override
    public void consumeFromApi(ConsumerRecord<String, Message> message) {
        System.out.println(message);
        log.info("{}", message);
        messageRepository.save(message.value());
        // logic ops will be added!
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }
}
