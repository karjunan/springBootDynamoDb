package com.softvision.springbootDynamodb.controller;

import com.softvision.springbootDynamodb.domain.Customer;
import com.softvision.springbootDynamodb.repository.CustomerRegistrationRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Component
@Path("register")
public class CustomerRegistrationController {

    @Inject
    CustomerRegistrationRepository registrationRepository;

    @GET
    @Path("/add")
    public void registerCustomer(@Suspended AsyncResponse asyncResponse) {

        Customer customer = new Customer();
        registrationRepository.save(customer);
        asyncResponse.resume("Successfully created table");
    }



}
