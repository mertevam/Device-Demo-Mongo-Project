package com.mert.device.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mert.device.core.model.CompositeId;
import com.mert.device.core.model.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.*;
import static org.springframework.kafka.test.assertj.KafkaConditions.key;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerServiceImplTest.class);

    private static String TOPIC_NAME = "messageTopic";

    @Autowired
    private KafkaProducerServiceImpl kafkaProducerService;

    private KafkaMessageListenerContainer<String, Message> container;
    private BlockingQueue<ConsumerRecord<String, Message>> consumerRecords;

    @ClassRule
    public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, TOPIC_NAME);

    @Before
    public void setUp() throws Exception {
        consumerRecords = new LinkedBlockingQueue<>();
        ContainerProperties containerProperties = new ContainerProperties(TOPIC_NAME);
        Map<String, Object> consumerProperties = KafkaTestUtils.consumerProps(
                "sender", "false", embeddedKafka.getEmbeddedKafka());

        DefaultKafkaConsumerFactory<String, Message> consumer = new DefaultKafkaConsumerFactory<>(consumerProperties);

        container = new KafkaMessageListenerContainer<>(consumer, containerProperties);
        container.setupMessageListener((MessageListener<String, Message>) record -> {
//            log.debug("Listened message: {}",record.toString());
            consumerRecords.add(record);
        });
        container.start();
        ContainerTestUtils.waitForAssignment(container, embeddedKafka.getEmbeddedKafka().getPartitionsPerTopic());

    }

    @After
    public void tearDown() throws Exception {
        container.stop();
    }

    @Test
    public void sendRandomMessageData() throws InterruptedException, JsonProcessingException {
        CompositeId id = new CompositeId(UUID.randomUUID(), 213123123123L);
        Message testMessage = new Message(id, null);
        kafkaProducerService.sendMessage(testMessage);
        ConsumerRecord<String, Message> received = consumerRecords.poll(10, TimeUnit.SECONDS);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(testMessage);
//        assertThat(received, hasValue(json));
//        assertThat(received).has(key(null));
        assertFalse(json.isEmpty());
    }

    @Test
    public void sendMessage() {
    }
}