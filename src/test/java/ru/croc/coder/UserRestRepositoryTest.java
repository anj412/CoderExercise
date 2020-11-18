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
import ru.croc.coder.domain.User;
import ru.croc.coder.service.exceptions.NotFoundException;

import static org.junit.Assert.assertEquals;

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
		mockMvc.perform(MockMvcRequestBuilders.get("/users")).
				andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	public void testAuth() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/auth/1")).
				andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	/*@Test
	public void testFakeGetUsers2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users")).
				andExpect(MockMvcResultMatchers.);
	}*/

	/*@Test
	public void testRestrictionAccept () throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/exercises/5/solutions")).
				andExpect(MockMvcResultMatchers.
	}*/



}
