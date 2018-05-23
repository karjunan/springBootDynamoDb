package com.softvision.springbootDynamodb.config;

import com.softvision.springbootDynamodb.controller.CustomerRegistrationController;
import com.softvision.springbootDynamodb.repository.CustomerRegistrationRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CustomerRegistrationController.class);
    }

}
