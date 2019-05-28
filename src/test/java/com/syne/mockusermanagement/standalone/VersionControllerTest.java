package com.syne.mockusermanagement.standalone;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.syne.mockusermanagement.categories.IntegrationTest;
import com.syne.mockusermanagement.controller.VersionController;
import com.syne.mockusermanagement.rest.dto.Version;
import com.syne.mockusermanagement.service.VersionService;

@RunWith(SpringRunner.class)
@WebMvcTest(VersionController.class)
@Category(IntegrationTest.class)
public class VersionControllerTest {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private VersionService service;
	
	
	@Test
	public void whenVersion_getJson() throws Exception{
		Version v = new Version();
		v.setVersion("UNIT_TEST");
		
		
		when(service.version()).thenReturn(v);

		this.mvc.perform(get("/version"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{'version':'UNIT_TEST' }"));	
	}
	
	
}
