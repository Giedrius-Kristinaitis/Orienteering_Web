package com.clickbait.orient.config;

import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.database.UserRepository;
import com.clickbait.orient.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Bean configuration file
 */
@Configuration
public class BeanConfig {

    // application context used to get beans
    private ApplicationContext context;

    /**
     * Gets ModelMapper bean to map objects
     * @return
     */
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    /**
     * Gets user service implementation
     * @return
     */
    @Bean
    public UserService userService() {
        return new UserServiceImpl(context.getBean(UserRepository.class),
                context.getBean(ModelMapper.class));
    }

    /**
     * Gets event service implementation
     * @return
     */
    @Bean
    public EventService eventService() {
        return new EventServiceImpl(context.getBean(EventRepository.class));
    }

    /**
     * Gets file service implementation
     * @return
     */
    @Bean
    public FileService fileService() {
        return new FileServiceImpl(context.getBean(FileConfig.class));
    }

    /**
     * Sets application context
     * @param context
     */
    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }
}
