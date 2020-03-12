package com.mert.device.api.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

//@ComponentScan(basePackages = "com.mert.device.core")
@EnableKafka
@Configuration
public class KafkaAppConfig {

}