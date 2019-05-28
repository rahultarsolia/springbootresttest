package com.syne.mockusermanagement.standalone;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.syne.mockusermanagement.categories.IntegrationTest;
import com.syne.mockusermanagement.controller.CustomerController;
import com.syne.mockusermanagement.exceptions.NotFoundException;
import com.syne.mockusermanagement.greetings.Customer;
import com.syne.mockusermanagement.greetings.Greeting;
import com.syne.mockusermanagement.rest.dto.AgeInfo;
import com.syne.mockusermanagement.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@Category(IntegrationTest.class)
public class CustomerControllerTest {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private CustomerService service;
	
	
	@Test
	public void whenGetCustomerGreeting_givenCustomerExists_returnJson() throws Exception{
		
		Greeting greeting = new Greeting(0, "Test Greeting");

		when(service.greetUser(1L)).thenReturn(greeting);
		
		this.mvc.perform(get("/customer/greet")
				.param("id", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{'id':0 , 'content':'Test Greeting'}"));	
	}
	
	@Test
	public void whenGetCustomer_givenCustomerExists_returnJson() throws Exception{
		
		Customer sampleCustomer = new Customer();
		sampleCustomer.setId(1L);
		sampleCustomer.setFirstName("Susan");
		sampleCustomer.setLastName("Ivanova");		
		LocalDate dob =  LocalDate.of(2000, 2, 23);
		sampleCustomer.setDob(dob );		

		when(service.findById(1L)).thenReturn(sampleCustomer);
		
		this.mvc.perform(get("/customer/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{'id':1 , 'firstName':'Susan', lastName:'Ivanova', dob:'2000-02-23'}"));	
	}
	
	@Test
	public void whenGetCustomer_givenCustomerIdBadFormat_return400() throws Exception{
		
		this.mvc.perform(get("/customer/BAD"))
			.andDo(print())
			.andExpect(status().isBadRequest());	
	}
	
	@Test
	public void whenGetCustomerAge_givenCustomerExistsAndFixedDate_returnJson() throws Exception{
		
		Customer sampleCustomer = new Customer();
		sampleCustomer.setId(1L);
		sampleCustomer.setFirstName("Susan");
		sampleCustomer.setLastName("Ivanova");		
		LocalDate dob =  LocalDate.of(2000, 2, 23);
		sampleCustomer.setDob(dob );
		
		LocalDate dateForAgeCalculation = LocalDate.of(2018, 2, 24);
		
		AgeInfo age = new AgeInfo();
		age.setAge(18);


		when(service.calculateCustomerAge(1L, Optional.of( dateForAgeCalculation))).thenReturn(age);
		
		// This is expected to fail as this returns an integer rather than
		this.mvc.perform(get("/customer/1/age")
				.param("onDate", "2018-02-24"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{'age':18}"));
//		.andExpect(content().json("{'over18':true , 'over60':false, ageRange:'18-24'}"));
	}
	
	@Test
	public void whenGetCustomerAge_givenDateIncorrectlyFormated_returnError400() throws Exception{
		
		
		this.mvc.perform(get("/customer/1/age")
				.param("onDate", "2018-BAD-24"))
			.andDo(print())
			.andExpect(status().isBadRequest());	
		//TODO Determine what error body we want to return.
	}
	
	@Test
	public void whenGetCustomer_andNoCustomer_returns404NotFound() throws Exception{
		when(service.findById(2L)).thenThrow(new NotFoundException());
		
		this.mvc.perform(get("/customer/2"))
			.andDo(print())
			.andExpect(status().isNotFound());
		//TODO Determine what error body we want to return.
	}
	
	
}
