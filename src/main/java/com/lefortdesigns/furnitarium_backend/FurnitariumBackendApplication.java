package com.lefortdesigns.furnitarium_backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FurnitariumBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FurnitariumBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
