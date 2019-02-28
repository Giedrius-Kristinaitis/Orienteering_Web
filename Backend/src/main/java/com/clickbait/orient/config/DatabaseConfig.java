package com.clickbait.orient.config;

import com.clickbait.orient.database.UserRepository;
import com.clickbait.orient.database.UserRepositoryMockImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configures the database
 */
@Configuration
@EnableMongoRepositories
public class DatabaseConfig {

    /**
     * Gets mock user repository implementation
     * @return mocked user repository
     */
    @Bean
    @Primary
    public UserRepository userRepository() {
        return new UserRepositoryMockImpl();
    }
}
