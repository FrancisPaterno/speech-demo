package com.assesment.speeches.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assesment.speeches.model.SpeechSearchCriteria;
import com.assesment.speeches.model.Speeches;
import com.assesment.speeches.service.SpeechService;

@RestController
@RequestMapping("api/v1/speech")
public class SpeechController {

	private final SpeechService speechService;

	@Autowired
	public SpeechController(SpeechService speechService) {
		this.speechService = speechService;
	}

	@GetMapping("{politician_id}")
	public List<Speeches> getSpeechesByPolitician(@PathVariable("politician_id") Long id ){
		return speechService.getSpeechesByPolitician(id);
	}

	@GetMapping("search/{politician_id}")
	public List<Speeches> getSpeechesBySearchCriteria(@PathVariable("politician_id") Long id, @Valid SpeechSearchCriteria searchCriteria){
		return speechService.getSpeechesByCriteria(id, searchCriteria);
	}

	@PostMapping
	public ResponseEntity<Speeches> addSpeech(@RequestBody @Valid Speeches speech){
		return speechService.addSpeech(speech);
	}

	@PutMapping("{speechId}")
	public ResponseEntity<Speeches> updateSpeech(@PathVariable("speechId") Long id, @RequestBody @Valid Speeches speech){
		return speechService.updateSpeech(id, speech);
	}

	@DeleteMapping("{speechId}")
	public ResponseEntity<Map<String, Boolean>> deleteSpeech(@PathVariable("speechId")Long id){
		return speechService.deleteSpeech(id);
	}
}
