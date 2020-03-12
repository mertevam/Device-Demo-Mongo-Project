package com.mert.device.api.configuration;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.google.gson.Gson;
import com.mert.device.core.model.DeviceDataTest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaDataConsumerConfig {

//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "MESSAGEDATA");
//
//        return new DefaultKafkaConsumerFactory<>(config);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
//        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
//        return concurrentKafkaListenerContainerFactory;
//    }

    // ------------------------

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value(value = "${kafka.topic.data.groupName}")
    private String dataGroupName;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeviceDataTest> messageKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DeviceDataTest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(deviceDataTestConsumerFactory());
        return factory;
    }

    private Map<String, Object> deviceDataTestConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, dataGroupName);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    public ConsumerFactory<String, DeviceDataTest> deviceDataTestConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(deviceDataTestConsumerConfigs(),
                new org.apache.kafka.common.serialization.StringDeserializer(),
                new org.springframework.kafka.support.serializer.JsonDeserializer<>(DeviceDataTest.class));
    }

}