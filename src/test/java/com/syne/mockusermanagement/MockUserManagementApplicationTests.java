package com.syne.mockusermanagement;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.syne.mockusermanagement.categories.IntegrationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTest.class)
public class MockUserManagementApplicationTests {

	
	@Test
	public void loadContext() {
		
	}
	
	
}
