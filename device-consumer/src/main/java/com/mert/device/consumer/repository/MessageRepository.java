package com.mert.device.consumer.repository;

import com.mert.device.core.model.CompositeId;
import com.mert.device.core.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MessageRepository extends MongoRepository<Message, CompositeId> {
}
