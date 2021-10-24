package com.assesment.speeches.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assesment.speeches.model.SharedSpeeches;
import com.assesment.speeches.service.SharedSpeechService;

@RestController
@RequestMapping("api/v1/sharespeech")
public class SharedSpeechController {

	private final SharedSpeechService sharedSpeechService;

	public SharedSpeechController(SharedSpeechService sharedSpeechService) {
		this.sharedSpeechService = sharedSpeechService;
	}

	@GetMapping("{politician_id}")
	public List<SharedSpeeches> getSharedSpeechesByPolitician(@PathVariable("politician_id") Long id ){
		return sharedSpeechService.getSharedSpeechesByPolitician(id);
	}

	@PostMapping
	public ResponseEntity<SharedSpeeches> shareSpeech(@RequestBody @Valid SharedSpeeches sharedSpeeches) {
		return this.sharedSpeechService.shareSpeech(sharedSpeeches);
	}
}
