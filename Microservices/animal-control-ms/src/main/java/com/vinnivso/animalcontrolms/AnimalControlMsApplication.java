package com.vinnivso.animalcontrolms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AnimalControlMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalControlMsApplication.class, args);
    }
}
