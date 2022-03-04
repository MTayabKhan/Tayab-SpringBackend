package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.domain.Hunters;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up MockMVC objects
@Sql(scripts = { "classpath:hunters-schema.sql",
		"classpath:hunters-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class HuntersControllerIntegrationTest {
	@Autowired // pull the MockMvc object from context (
	private MockMvc mvc; // Class that performs our requests, like postman
	@Autowired
	private ObjectMapper mapper; // java <-> JSON converter that Spring uses
	
	
	@Test // This tests if you can create a record with name Ludwig, blood echoes at 10,
			// oldBloodFeared is false
	void testCreate() throws Exception {
		Hunters testHunters = new Hunters(null, "Ludwig", 10, false);
		String testHuntersAsJSON = this.mapper.writeValueAsString(testHunters);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testHuntersAsJSON);

		Hunters testCreatedHunters = new Hunters(3, "Ludwig", 10, false);
		String testCreatedHuntersAsJSON = this.mapper.writeValueAsString(testCreatedHunters);
		ResultMatcher checkStatus = status().isCreated(); // checks if Status code 201 is returned
		ResultMatcher checkBody = content().json(testCreatedHuntersAsJSON); // Checks if body matches my
																			// testCreatedHunters

		// will send a request and then checks status and body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getAllTest() throws Exception{
		RequestBuilder req = get("/getAll");
		List<Hunters> testHunters = List.of(new Hunters(1, "Patches", 333, true), new Hunters(2, "Djura", 333, false));
		String json = this.mapper.writeValueAsString(testHunters);
		ResultMatcher checkStatus = status().isOk(); 	// checks if status code is returned
		ResultMatcher checkBody = content().json(json); //checks if the correct body is returned
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); //this checks both the parameters above
		
	}
	
	
	@Test // This tests if when you ask for the ID of the first record you get the correct
			// one, which is named Patches, oldblood is feared and blood echoes are 333
	void getIdTest() throws Exception {
		RequestBuilder req = get("/get/1");
		Hunters testHunt = new Hunters(1, "Patches", 333, true);
		String json = this.mapper.writeValueAsString(testHunt);
		ResultMatcher checkStatus = status().isOk(); // checks if status code is returned
		ResultMatcher checkBody = content().json(json); // checks if the correct body is returned

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); // this checks both the parameters above
	}

	@Test // this replaces record 2, changing "Djura" to Maria, changing the blood echoes
			// to 222, and making oldBloodFeared true
	void replaceTest() throws Exception {
		Hunters testHunters = new Hunters(null, "Maria", 222, true);
		String testHuntersAsJSON = this.mapper.writeValueAsString(testHunters);
		RequestBuilder req = put("/replace/2").contentType(MediaType.APPLICATION_JSON).content(testHuntersAsJSON);

		Hunters testUpdatedHunters = new Hunters(2, "Maria", 222, true);
		String testUpdatedHuntersAsJSON = this.mapper.writeValueAsString(testUpdatedHunters);
		ResultMatcher checkStatus = status().isAccepted(); // checks if Status code 202 is returned
		ResultMatcher checkBody = content().json(testUpdatedHuntersAsJSON); // Checks if body matches my
																			// myUpdatedTarnished

		// will send a request and then checks status and body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void removeTest() throws Exception {
		RequestBuilder req = delete("/remove/1");
		ResultMatcher checkStatus = status().isNoContent(); // checks if status code "no content" is returned

		this.mvc.perform(req).andExpect(checkStatus);
	}
}
