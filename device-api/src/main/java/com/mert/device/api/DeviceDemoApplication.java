package com.mert.device.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;


// By default, @EnableMongoRepositories scans the current package for any interfaces
// that extend one of Spring Data’s repository interfaces.
// You can use its basePackageClasses=MyRepository.class to safely tell Spring Data MongoDB
// to scan a different root package by type if your project layout has multiple projects
// and it does not find your repositories.

@SpringBootApplication
public class DeviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceDemoApplication.class, args);
    }

}
