package com.puce.java.gestionhospital.historial_medico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HistorialMedicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HistorialMedicoApplication.class, args);
    }

}
