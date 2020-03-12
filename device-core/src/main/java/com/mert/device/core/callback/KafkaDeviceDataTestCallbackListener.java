package com.mert.device.core.callback;

import com.mert.device.core.model.DeviceDataTest;
import com.mert.device.core.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class KafkaDeviceDataTestCallbackListener implements ListenableFutureCallback<SendResult<String, DeviceDataTest>> {

    @Override
    public void onFailure(Throwable ex) {
        log.info("Kafka device data stream fail due to: {}", ex.getMessage());
    }

    @Override
    public void onSuccess(SendResult<String, DeviceDataTest> result) {
        log.info("Kafka device data stream success, {}", result);
    }
}
