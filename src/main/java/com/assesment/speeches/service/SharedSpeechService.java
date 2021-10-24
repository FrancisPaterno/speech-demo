package com.assesment.speeches.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assesment.speeches.model.SharedSpeeches;
import com.assesment.speeches.repository.SharedSpeechRepository;

@Service
public class SharedSpeechService {

	private SharedSpeechRepository sharedSpeechRepository;

	public SharedSpeechService(SharedSpeechRepository sharedSpeechRepository) {
		this.sharedSpeechRepository = sharedSpeechRepository;
	}

	public ResponseEntity<SharedSpeeches> shareSpeech(SharedSpeeches sharedSpeech){
		SharedSpeeches newSharedSpeeches = this.sharedSpeechRepository.save(sharedSpeech);
		return new ResponseEntity<SharedSpeeches>(newSharedSpeeches, HttpStatus.CREATED);
	}

	public List<SharedSpeeches> getSharedSpeechesByPolitician(Long id){
		return sharedSpeechRepository.getSharedSpeeches(id);
	}
}
