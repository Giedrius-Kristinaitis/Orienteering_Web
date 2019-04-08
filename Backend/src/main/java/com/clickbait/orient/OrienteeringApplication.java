package com.clickbait.orient;

import com.clickbait.orient.config.FileConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Launches the Spring Boot application
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableConfigurationProperties({FileConfig.class})
public class OrienteeringApplication {

    /**
     * Entry point of the program
     * @param args arguments for the program
     */
    public static void main(String[] args) {
        SpringApplication.run(OrienteeringApplication.class, args);
    }
}
