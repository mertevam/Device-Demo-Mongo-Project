package com.mert.device.consumer.controller;

import com.mert.device.consumer.service.impl.KafkaConsumerServiceImpl;
import com.mert.device.core.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/messages")
public class KafkaConsumerController {

    private final KafkaConsumerServiceImpl consumerService;

    @Autowired
    public KafkaConsumerController(KafkaConsumerServiceImpl consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/get")
    public List<Message> getDevices() {
        log.info("getDevices()");
        return consumerService.getMessages();
    }
}
