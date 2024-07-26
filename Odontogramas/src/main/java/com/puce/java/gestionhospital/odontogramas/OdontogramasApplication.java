package com.puce.java.gestionhospital.odontogramas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OdontogramasApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdontogramasApplication.class, args);
    }

}
