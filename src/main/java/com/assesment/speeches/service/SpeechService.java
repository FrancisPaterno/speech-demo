package com.assesment.speeches.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assesment.speeches.model.SpeechSearchCriteria;
import com.assesment.speeches.model.Speeches;
import com.assesment.speeches.repository.SpeechCriteriaRepository;
import com.assesment.speeches.repository.SpeechRepository;

@Service
public class SpeechService {

	private SpeechRepository speechRepository;

	private SpeechCriteriaRepository speechCriteriaRepository;

	public SpeechService(SpeechRepository speechRepository, @Qualifier("main")SpeechCriteriaRepository speechCriteriaRepository) {
		this.speechRepository = speechRepository;
		this.speechCriteriaRepository = speechCriteriaRepository;
	}

	public List<Speeches> getSpeechesByPolitician(Long politician_id){
		return this.speechRepository.getSpeechesByPolitician(politician_id);
	}

	public List<Speeches> getSpeechesByCriteria(Long id, SpeechSearchCriteria searchCriteria){
		return speechCriteriaRepository.getSpeechesByCriteria(id, searchCriteria);
	}

	public ResponseEntity<Speeches> addSpeech(Speeches speech) {
		Speeches newSpeech = this.speechRepository.save(speech);
		return new ResponseEntity<Speeches>(newSpeech, HttpStatus.CREATED);
	}

	public ResponseEntity<Speeches> updateSpeech(Long id, Speeches updSpeech){
		Speeches speech = speechRepository.findById(id).orElseThrow(()->new IllegalStateException("Speech not found"));

		speech.setAuthor(updSpeech.getAuthor());
		speech.setContent(updSpeech.getContent());
		speech.setDate(updSpeech.getDate());
		speech.setPolitician(updSpeech.getPolitician());
		speech.setSubject_area(updSpeech.getSubject_area());

		Speeches updatedSpeech = speechRepository.save(speech);

		return ResponseEntity.ok(updatedSpeech);
	}

	public ResponseEntity<Map<String, Boolean>> deleteSpeech(Long id){
		Boolean isSpeechExists = speechRepository.existsById(id);

		if(!isSpeechExists) {
			throw new IllegalStateException("Speech does not exist!");
		}

		speechRepository.deleteById(id);
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		res.put("Deleted", Boolean.TRUE);

		return ResponseEntity.ok(res);
	}
}
