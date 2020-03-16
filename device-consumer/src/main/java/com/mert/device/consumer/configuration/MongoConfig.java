package com.mert.device.consumer.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.mert.device.consumer.repository")
public class MongoConfig {

}
