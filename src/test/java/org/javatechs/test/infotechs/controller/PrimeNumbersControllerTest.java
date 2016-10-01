package org.javatechs.test.infotechs.controller;

import org.javatechs.test.infotechs.TestOneApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;

/**
 * Created by Gleb on 01.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestOneApplication.class)
@WebAppConfiguration
public class PrimeNumbersControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void calculate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/prime/calculate")
				.param("input", "15"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.result", contains(3,5)))
				.andDo(MockMvcResultHandlers.print());
		mockMvc.perform(MockMvcRequestBuilders.post("/prime/calculate")
				.param("input", "15654745756856"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error", equalTo("Can`t get multiplyer from prime table")))
				.andDo(MockMvcResultHandlers.print());
		mockMvc.perform(MockMvcRequestBuilders.post("/prime/calculate")
				.param("input", "-15"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error", equalTo("Input can`t be <= 1")))
				.andDo(MockMvcResultHandlers.print());
	}

}