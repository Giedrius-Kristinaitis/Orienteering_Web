package com.clickbait.orient.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Bean configuration file
 */
@Configuration
public class BeanConfig {

    /**
     * Gets ModelMapper bean to map objects
     * @return
     */
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
