package ru.croc.coder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class UserRestRepositoryTest {
	
	@Autowired
	private WebApplicationContext applicationContext;
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init () {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();		
	}
	
	@Test
	public void testGetUsers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
}
