package com.syne.mockusermanagement.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.syne.mockusermanagement.exceptions.NotFoundException;
import com.syne.mockusermanagement.greetings.Customer;
import com.syne.mockusermanagement.greetings.Greeting;
import com.syne.mockusermanagement.repository.CustomerRepository;
import com.syne.mockusermanagement.rest.dto.AgeInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

	public String findFullName(Long customerID) {
		Optional<Customer> customer = customerRepository.findById(customerID);

		String name = customer.map(c -> {
			return c.getFirstName() + " " + c.getLastName();
		}).orElse("Guest");
		return name;
	}

	public Customer addOrUpdateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	
	
	private int calculateAge(
			  LocalDate birthDate,
			  LocalDate currentDate) {
			    // validate inputs ...
			    return Period.between(birthDate, currentDate).getYears();
			}
	
	
	public AgeInfo calculateCustomerAge(Long id, Optional<LocalDate> onDate) {
		Customer customer = findById(id);	
		
		int ageInYears = calculateAge(customer.getDob(), onDate.orElse(LocalDate.now()));
		
		AgeInfo age = new AgeInfo();
			
		age.setAge(ageInYears);
		
		
		return age;	
	}
	
	public Customer findById(Long id) {
		Optional<Customer> optional = customerRepository.findById(id);

		if (!optional.isPresent())
			throw new NotFoundException();

		return optional.get();
	}

	public Greeting greetUser(Long id) {
		return new Greeting(counter.incrementAndGet(),
                String.format(template, findFullName( id )));
	}

}
