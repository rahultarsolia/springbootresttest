package com.syne.mockusermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syne.mockusermanagement.greetings.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
