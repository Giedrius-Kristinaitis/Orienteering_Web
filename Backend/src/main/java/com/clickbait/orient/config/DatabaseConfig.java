package com.clickbait.orient.config;

import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.database.EventRepositoryMockImpl;
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

    /**
     * Gets mock event repository implementation
     * @return mocked event repository
     */
    @Bean
    @Primary
    public EventRepository eventRepository() {
        return new EventRepositoryMockImpl();
    }
}
