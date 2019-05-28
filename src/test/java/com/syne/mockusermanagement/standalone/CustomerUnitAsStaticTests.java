package com.syne.mockusermanagement.standalone;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.syne.mockusermanagement.categories.UnitTest;
import com.syne.mockusermanagement.greetings.Customer;
import com.syne.mockusermanagement.repository.CustomerRepository;
import com.syne.mockusermanagement.service.CustomerDao;
import com.syne.mockusermanagement.service.CustomerService;


@Category(UnitTest.class)
public class CustomerUnitAsStaticTests {

	@Test
	public void findFullName(){
		
		Customer sampleCustomer = new Customer();
		sampleCustomer.setFirstName("Susan");
		sampleCustomer.setLastName("Ivanova");
		
		CustomerRepository customerRepository = mock(CustomerRepository.class);
		when(customerRepository.findById(1L)).thenReturn(Optional.of(sampleCustomer));
		
		CustomerService customerService = new CustomerService(customerRepository);
		String customerName = customerService.findFullName(1L);
		
		Assert.assertEquals( sampleCustomer.getFirstName() +" "+ sampleCustomer.getLastName(),  customerName);

	}
	
	@Test
	public void findGuestFullName(){
		
		CustomerRepository customerRepository = mock(CustomerRepository.class);
		when(customerRepository.findById(1L)).thenReturn(Optional.empty());
		
		CustomerService customerService = new CustomerService(customerRepository);
		String customerName = customerService.findFullName(1L);
		
		Assert.assertEquals( "Guest",  customerName);
		

	}
	
}
