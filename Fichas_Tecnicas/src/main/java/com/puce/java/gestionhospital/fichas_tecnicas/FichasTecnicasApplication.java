package com.puce.java.gestionhospital.fichas_tecnicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FichasTecnicasApplication {

    public static void main(String[] args) {
        SpringApplication.run(FichasTecnicasApplication.class, args);
    }

}
