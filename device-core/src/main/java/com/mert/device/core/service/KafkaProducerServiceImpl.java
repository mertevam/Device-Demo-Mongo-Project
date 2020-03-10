package com.mert.device.core.service;

import com.mert.device.core.callback.KafkaMessageCallbackListener;
import com.mert.device.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, Message> messageKafkaTemplate;
    private final KafkaMessageCallbackListener messageCallbackListener;

    @Value(value = "${kafka.topic.message.topicName}")
    private String messageTopicName;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, Message> messageKafkaTemplate) {
        this.messageKafkaTemplate = messageKafkaTemplate;
        this.messageCallbackListener = new KafkaMessageCallbackListener();
    }

    @Scheduled(fixedDelay = 10 * 1000L, initialDelay = 10L * 3)
    public void sendRandomMessageData() {
        String context = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();
        Message message = new Message(context, timestamp);
        sendMessage(message);
    }

    @Override
    public void sendMessage(Message message) {
//        int partition = Math.abs(key.hashCode()) % messageTopicPartition;

        ListenableFuture<SendResult<String, Message>> messageTopic = messageKafkaTemplate.send(messageTopicName, message);
        messageTopic.addCallback(messageCallbackListener);
    }

}
