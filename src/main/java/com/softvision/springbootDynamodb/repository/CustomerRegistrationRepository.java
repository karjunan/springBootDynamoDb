package com.softvision.springbootDynamodb.repository;


import com.softvision.springbootDynamodb.domain.Customer;


public interface CustomerRegistrationRepository {

     void save(Customer customer);



}
