package com.assesment.speeches.repository;

import java.util.List;

import com.assesment.speeches.model.SpeechSearchCriteria;
import com.assesment.speeches.model.Speeches;

public interface SpeechCriteriaRepository {

	public List<Speeches> getSpeechesByCriteria(Long id, SpeechSearchCriteria speechSearchCriteria);
}
