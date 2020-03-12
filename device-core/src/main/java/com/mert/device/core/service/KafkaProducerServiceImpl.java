package com.mert.device.core.service;

import com.mert.device.core.callback.KafkaDeviceDataTestCallbackListener;
import com.mert.device.core.callback.KafkaMessageCallbackListener;
import com.mert.device.core.model.DeviceDataTest;
import com.mert.device.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, Message> messageKafkaTemplate;
    private final KafkaMessageCallbackListener messageCallbackListener;
    private final KafkaTemplate<String, DeviceDataTest> deviceDataTestKafkaTemplate;
    private final KafkaDeviceDataTestCallbackListener deviceDataTestCallbackListener;

    @Value(value = "${kafka.topic.message.topicName}")
    private String messageTopicName;
    @Value(value = "${kafka.topic.data.topicName}")
    private String dataTopicName;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, Message> messageKafkaTemplate, KafkaTemplate<String, DeviceDataTest> deviceDataTestKafkaTemplate) {
        this.messageKafkaTemplate = messageKafkaTemplate;
        this.deviceDataTestKafkaTemplate = deviceDataTestKafkaTemplate;
        this.deviceDataTestCallbackListener = new KafkaDeviceDataTestCallbackListener();
//        this.deviceDataTestKafkaTemplate = deviceDataTestKafkaTemplate;
//        this.deviceDataTestCallbackListener = deviceDataTestCallbackListener;
        this.messageCallbackListener = new KafkaMessageCallbackListener();
    }

    @Scheduled(fixedDelay = 10 * 1000L, initialDelay = 10L * 3)
    public void sendRandomMessageData() {
        UUID id = UUID.randomUUID();
        long timestamp = System.currentTimeMillis();
        Map<String, BigDecimal> parameters = new HashMap<>();
        Message message = new Message(id, timestamp, parameters);
        sendMessage(message);
    }

    @Scheduled(fixedDelay = 10 * 1000L, initialDelay = 10L * 3)
    public void sendRandomDeviceDataTest() {
        UUID id = UUID.randomUUID();
        long timestamp = System.currentTimeMillis();
        Map<String, BigDecimal> parameters = new HashMap<>();
        DeviceDataTest deviceDataTest = new DeviceDataTest(id, timestamp, parameters);
        sendDeviceDataTest(deviceDataTest);
    }

    @Override
    public void sendMessage(Message message) {
//        int partition = Math.abs(key.hashCode()) % messageTopicPartition;

        ListenableFuture<SendResult<String, Message>> messageTopic = messageKafkaTemplate.send(messageTopicName, message);
        messageTopic.addCallback(messageCallbackListener);
    }

    @Override
    public void sendDeviceDataTest(DeviceDataTest deviceDataTest) {
        ListenableFuture<SendResult<String, DeviceDataTest>> deviceDataTopic = deviceDataTestKafkaTemplate.send(dataTopicName, deviceDataTest);
        deviceDataTopic.addCallback(deviceDataTestCallbackListener);
    }

}
