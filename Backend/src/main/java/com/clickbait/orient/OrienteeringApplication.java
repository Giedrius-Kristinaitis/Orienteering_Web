package com.clickbait.orient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * Launches the Spring Boot application
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class OrienteeringApplication {

    /**
     * Entry point of the program
     * @param args arguments for the program
     */
    public static void main(String[] args) {
        SpringApplication.run(OrienteeringApplication.class, args);
    }
}
