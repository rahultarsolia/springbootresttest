package com.syne.mockusermanagement.service;

import com.syne.mockusermanagement.greetings.Customer;

public interface CustomerDao {
    Customer findCustomerById(String customerId);
    Customer findCustomerByName(String firstName, String lastName);
    void deleteCustomer(Customer customer);
}

