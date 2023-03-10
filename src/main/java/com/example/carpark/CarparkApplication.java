package com.example.carpark;

import com.example.carpark.common.repository.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class CarparkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarparkApplication.class, args);
    }

}
