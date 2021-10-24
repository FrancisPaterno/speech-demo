package com.assesment.speeches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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

import com.assesment.speeches.controller.SharedSpeechController;
import com.assesment.speeches.model.Authors;
import com.assesment.speeches.model.Politicians;
import com.assesment.speeches.model.SharedSpeeches;
import com.assesment.speeches.model.Speeches;
import com.assesment.speeches.service.SharedSpeechService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(SharedSpeechController.class)
public class SharedSpeechControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SharedSpeechService sharedSpeechService;

	@Test
	public void shareSpceechTest() throws Exception {
		SharedSpeeches sp = new SharedSpeeches(new Speeches("My sample content", "My subject area", new Authors("Francis Paterno"), LocalDate.of(2021, 10, 11), new Politicians("John Doe", "john.doe@gmail.com")), "francis.paterno@gmail.com");

		Mockito.when(sharedSpeechService.shareSpeech(ArgumentMatchers.any(SharedSpeeches.class))).thenReturn(new ResponseEntity<SharedSpeeches>(sp,HttpStatus.CREATED));

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String InputInJson = mapper.writeValueAsString(sp);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sharespeech")
				.content(InputInJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();

		String OutputInJson = response.getContentAsString();

		MatcherAssert.assertThat(InputInJson, Matchers.equalTo(OutputInJson));
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	public void shareSpceechTestWithNullEmail() throws Exception {
		SharedSpeeches sp = new SharedSpeeches(new Speeches("My sample content", "My subject area", new Authors("Francis Paterno"), LocalDate.of(2021, 10, 11), new Politicians("John Doe", "john.doe@gmail.com")), null);

		Mockito.when(sharedSpeechService.shareSpeech(ArgumentMatchers.any(SharedSpeeches.class))).thenReturn(new ResponseEntity<SharedSpeeches>(sp,HttpStatus.CREATED));

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String InputInJson = mapper.writeValueAsString(sp);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sharespeech")
				.content(InputInJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void shareSpceechTestWithBlankEmail() throws Exception {
		SharedSpeeches sp = new SharedSpeeches(new Speeches("My sample content", "My subject area", new Authors("Francis Paterno"), LocalDate.of(2021, 10, 11), new Politicians("John Doe", "john.doe@gmail.com")), "");

		Mockito.when(sharedSpeechService.shareSpeech(ArgumentMatchers.any(SharedSpeeches.class))).thenReturn(new ResponseEntity<SharedSpeeches>(sp,HttpStatus.CREATED));

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String InputInJson = mapper.writeValueAsString(sp);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sharespeech")
				.content(InputInJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void shareSpceechTestWithInvalidEmail() throws Exception {
		SharedSpeeches sp = new SharedSpeeches(new Speeches("My sample content", "My subject area", new Authors("Francis Paterno"), LocalDate.of(2021, 10, 11), new Politicians("John Doe", "john.doe@gmail.com")), "francis.com");

		Mockito.when(sharedSpeechService.shareSpeech(ArgumentMatchers.any(SharedSpeeches.class))).thenReturn(new ResponseEntity<SharedSpeeches>(sp,HttpStatus.CREATED));

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String InputInJson = mapper.writeValueAsString(sp);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sharespeech")
				.content(InputInJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
}
