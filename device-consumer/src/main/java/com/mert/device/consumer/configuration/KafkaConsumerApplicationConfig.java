package com.mert.device.consumer.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@ComponentScan(basePackages = "com.mert.device")
@EnableKafka
public class KafkaConsumerApplicationConfig {

}
