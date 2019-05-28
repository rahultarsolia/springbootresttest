package com.syne.mockusermanagement.standalone;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.syne.mockusermanagement.categories.UnitTest;
import com.syne.mockusermanagement.greetings.Customer;
import com.syne.mockusermanagement.repository.CustomerRepository;
import com.syne.mockusermanagement.rest.dto.AgeInfo;
import com.syne.mockusermanagement.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
@Category(UnitTest.class)
public class CustomerServiceAsPrivateTest {

	@Mock
	CustomerRepository customerRepository;
	
	@Test
	public void findFullName(){
		
		Customer sampleCustomer = new Customer();
		sampleCustomer.setFirstName("Susan");
		sampleCustomer.setLastName("Ivanova");
		
		final String expectedName = sampleCustomer.getFirstName() +" "+ sampleCustomer.getLastName();
	
		when(customerRepository.findById(1L)).thenReturn(Optional.of(sampleCustomer));
		
		CustomerService customerService = new CustomerService(customerRepository);
		String customerName = customerService.findFullName(1L);
		
		Assert.assertEquals( expectedName ,  customerName);

	}
	
	
	@Test
	public void whenCalculateAge_givenCustomerExistsAndFixedDate_returnJson() throws Exception{
		
		Customer sampleCustomer = new Customer();
		sampleCustomer.setId(1L);
		sampleCustomer.setFirstName("Susan");
		sampleCustomer.setLastName("Ivanova");		
		LocalDate dob =  LocalDate.of(2000, 2, 23);
		sampleCustomer.setDob(dob );		
		
		when(customerRepository.findById(1L)).thenReturn(Optional.of(sampleCustomer));

		CustomerService customerService = new CustomerService(customerRepository);
		AgeInfo age = customerService.calculateCustomerAge(1L, Optional.ofNullable(null));
		
		
		// Not this test will fail if the computer date is incorrect and set before
		// 2018
		assertThat("age", age.getAge(), greaterThan(18));
		
	}
	
	
	@Test
	public void customerAgeOnDate(){
		
		Customer sampleCustomer = new Customer();
		sampleCustomer.setFirstName("Susan");
		sampleCustomer.setLastName("Ivanova");
		LocalDate dob =  LocalDate.of(2000, 2, 23);
		sampleCustomer.setDob(dob );	

		// Customer will be 18
		LocalDate onDate =  LocalDate.of(2018, 2, 24);
		
		when(customerRepository.findById(1L)).thenReturn(Optional.of(sampleCustomer));
		
		CustomerService customerService = new CustomerService(customerRepository);
		AgeInfo age = customerService.calculateCustomerAge(1L, Optional.of(onDate));
		
		assertThat("age", age.getAge(), equalTo(18));
		
	}
	
	@Test
	public void findGuestFullName(){

		when(customerRepository.findById(1L)).thenReturn(Optional.empty());
		
		CustomerService customerService = new CustomerService(customerRepository);
		String customerName = customerService.findFullName(1L);
		
		Assert.assertEquals( "Guest",  customerName);
		verify(customerRepository, times(1)).findById(1L);
		verify(customerRepository, never()).deleteById(1L);
		
	}
	
	
		

}
