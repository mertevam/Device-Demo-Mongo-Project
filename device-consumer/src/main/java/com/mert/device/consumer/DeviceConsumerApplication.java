package com.mert.device.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class DeviceConsumerApplication {

	public static void main(String[] args) {
		run(DeviceConsumerApplication.class, args);

		System.out.println("Device consumer module");
	}

}
