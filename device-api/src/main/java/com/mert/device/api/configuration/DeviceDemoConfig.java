package com.mert.device.api.configuration;

import com.mert.device.core.config.KafkaProducerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

//@Import(KafkaProducerConfig.class)
//@Configuration
//@ComponentScan("com.mert.device")
@Configuration
@EnableKafka
@Import(KafkaProducerConfig.class)
public class DeviceDemoConfig {

}
