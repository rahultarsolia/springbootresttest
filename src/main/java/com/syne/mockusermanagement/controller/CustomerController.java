package com.syne.mockusermanagement.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syne.mockusermanagement.greetings.Customer;
import com.syne.mockusermanagement.greetings.Greeting;
import com.syne.mockusermanagement.rest.dto.AgeInfo;
import com.syne.mockusermanagement.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final CustomerService customerService;

    
    @PostMapping("")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addOrUpdateCustomer(customer);
    }

    @GetMapping("/greet")
    public Greeting greeting(@RequestParam(value="id", defaultValue="0") Long name) {
        return customerService.greetUser(name);
    }
    
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.findById(id);
    }
    
    @GetMapping("/{id}/age")
    public AgeInfo getCustomerAge(@PathVariable("id") Long id, 
    	     @RequestParam(name = "onDate", required = false)
    	     @DateTimeFormat(iso = ISO.DATE)
    	     Optional<LocalDate> onDate) {
        return customerService.calculateCustomerAge(id, onDate);
    }

}
