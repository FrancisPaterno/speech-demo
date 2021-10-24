package com.assesment.speeches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assesment.speeches.controller.SpeechController;
import com.assesment.speeches.model.Authors;
import com.assesment.speeches.model.Politicians;
import com.assesment.speeches.model.SpeechSearchCriteria;
import com.assesment.speeches.model.Speeches;
import com.assesment.speeches.service.SpeechService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(SpeechController.class)
public class SpeechControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SpeechService speechService;

	@Test
	public void saveSpeechTest() throws JsonProcessingException, Exception {
		Authors author = new Authors("Francis Paterno");
		Politicians politician1 = new Politicians("Francis Paterno", "francis.paterno@gmail.com");
		Speeches speech = new Speeches("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
				"Lorem Ipsum", author,LocalDate.of(2021, 10, 23), politician1);
		Mockito.when(speechService.addSpeech(ArgumentMatchers.any(Speeches.class))).thenReturn(new ResponseEntity<Speeches>(speech, HttpStatus.CREATED));

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String InputInJson = mapper.writeValueAsString(speech);
		MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/speech")
				.content(InputInJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();

		String OutputInJson = response.getContentAsString();

		MatcherAssert.assertThat(InputInJson, Matchers.equalTo(OutputInJson));
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void saveSpeechTestSubjectAreaBlank() throws JsonProcessingException, Exception {
		Authors author = new Authors("Francis Paterno");
		Politicians politician1 = new Politicians("Francis Paterno", "francis.paterno@gmail.com");
		Speeches speech = new Speeches("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
				"", author,LocalDate.of(2021, 10, 23), politician1);
		Mockito.when(speechService.addSpeech(ArgumentMatchers.any(Speeches.class))).thenReturn(new ResponseEntity<Speeches>(speech, HttpStatus.CREATED));

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String InputInJson = mapper.writeValueAsString(speech);
		MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/speech")
				.content(InputInJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void updateSpeechTest() throws Exception {
		Speeches speech = new Speeches("Sample Content", "My subject area", new Authors("Naomi Paterno"), LocalDate.now(), new Politicians("Francis Paterno", "francis.paterno@gmail.com"));

		Mockito.when(speechService.updateSpeech(ArgumentMatchers.any(Long.class),ArgumentMatchers.any(Speeches.class))).thenReturn(new ResponseEntity<Speeches>(speech, HttpStatus.OK));

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String InputInJson = mapper.writeValueAsString(speech);
		MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/speech/1")
				.content(InputInJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();

		String OutputInJson = response.getContentAsString();

		MatcherAssert.assertThat(InputInJson, Matchers.equalTo(OutputInJson));
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void deleteSpeechTest() throws Exception {
		Speeches speech = new Speeches("Sample Content for delete", "My subject area for delete", new Authors("Odette Khan"), LocalDate.now(), new Politicians("Rodrigo Duterte", "rodrigo.duterte@gmail.com"));
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		res.put("Deleted", Boolean.TRUE);
		Mockito.when(speechService.deleteSpeech(ArgumentMatchers.any(Long.class))).thenReturn(new ResponseEntity<Map<String,Boolean>>(res,HttpStatus.OK));

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String inputJson = mapper.writeValueAsString(speech);
		String expectedOutput = mapper.writeValueAsString(res);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/speech/2")
				.content(inputJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();

		String OutputInJson = response.getContentAsString();

		MatcherAssert.assertThat(expectedOutput, Matchers.equalTo(OutputInJson));
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void getSpeechesByPoliticianTest() throws Exception {
		List<Speeches> list = new ArrayList<Speeches>();
		list.add(new Speeches("First Content", "First Subject area", new Authors("Mary Jane"), LocalDate.of(2021,10,1), new Politicians("Francis Paterno", "francis.paterno@gmail.com")));
		list.add(new Speeches("Second Content", "Second Subject area", new Authors("John Doe"), LocalDate.of(2021,10,2), new Politicians("Naomi Paterno", "naomi.paterno@gmail.com")));
		list.add(new Speeches("Third Content", "Third Subject area", new Authors("Jose Rizal"), LocalDate.of(2021,10,3), new Politicians("Rodrigo Duterte", "rodrigo.duterte@gmail.com")));

		Mockito.when(speechService.getSpeechesByPolitician(ArgumentMatchers.anyLong())).thenReturn(list);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/speech/1"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
	}

	@Test
	public void getSpeechesBySearchTest() throws Exception {
		List<Speeches> list = new ArrayList<Speeches>(); //Mockito.mock(ArrayList.class);

		list.add(new Speeches("First Content", "First Subject area", new Authors("Mary"), LocalDate.of(2021,10,1), new Politicians("Francis Paterno", "francis.paterno@gmail.com")));

		Mockito.when(speechService.getSpeechesByCriteria(ArgumentMatchers.anyLong(),ArgumentMatchers.any(SpeechSearchCriteria.class))).thenReturn(list);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/speech/search/1?category=AUTHOR&value=Mary"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("First Content")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].subject_area", Matchers.is("First Subject area")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].author.name", Matchers.is("Mary")));
	}

	@Test
	public void getSpeechesBySearchTestNoCategory() throws Exception {
		List<Speeches> list = new ArrayList<Speeches>(); //Mockito.mock(ArrayList.class);

		list.add(new Speeches("First Content", "First Subject area", new Authors("Mary"), LocalDate.of(2021,10,1), new Politicians("Francis Paterno", "francis.paterno@gmail.com")));

		Mockito.when(speechService.getSpeechesByCriteria(ArgumentMatchers.anyLong(),ArgumentMatchers.any(SpeechSearchCriteria.class))).thenReturn(list);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/speech/search/1"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
