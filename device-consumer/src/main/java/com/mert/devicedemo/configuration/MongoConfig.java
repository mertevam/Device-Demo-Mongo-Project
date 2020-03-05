package com.mert.devicedemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.mert.devicedemo.repository")
public class MongoConfig {

}
