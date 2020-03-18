package com.mert.device.consumer.controller;

import com.mert.device.consumer.repository.MessageRepository;
import com.mert.device.consumer.service.KafkaConsumerService;
import com.mert.device.consumer.service.impl.KafkaConsumerServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KafkaConsumerControllerTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Before
    public void setUp() {
        clear();

    }

    @After
    public void clear() {
        messageRepository.deleteAll();
    }


}
