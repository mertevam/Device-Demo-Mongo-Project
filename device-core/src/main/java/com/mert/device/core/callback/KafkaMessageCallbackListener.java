package com.mert.device.core.callback;

import com.mert.device.core.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class KafkaMessageCallbackListener implements ListenableFutureCallback<SendResult<String, Message>> {

    @Override
    public void onFailure(Throwable ex) {
        log.info("Kafka message stream fail due to: {}", ex.getMessage());
    }

    @Override
    public void onSuccess(SendResult<String, Message> result) {
        log.info("Kafka message stream success, {}", result);
    }
}
