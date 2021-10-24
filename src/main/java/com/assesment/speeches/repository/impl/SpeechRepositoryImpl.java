package com.assesment.speeches.repository.impl;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.assesment.speeches.model.SpeechSearchCriteria;
import com.assesment.speeches.model.Speeches;
import com.assesment.speeches.repository.SpeechCriteriaRepository;

@Repository("main")
public class SpeechRepositoryImpl implements SpeechCriteriaRepository {

	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;

	public SpeechRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
	}

	@Override
	public List<Speeches> getSpeechesByCriteria(Long id, SpeechSearchCriteria searchCriteria) {
		CriteriaQuery<Speeches> criteriaQuery = criteriaBuilder.createQuery(Speeches.class);
		Root<Speeches> root = criteriaQuery.from(Speeches.class);
		Predicate predicatePolitician = this.criteriaBuilder.equal(root.get("politician").get("id"), id);
		Predicate predicateCategory = getPredicate(searchCriteria, root);

		criteriaQuery.where(this.criteriaBuilder.and(predicatePolitician, predicateCategory));

		TypedQuery<Speeches> speeches = entityManager.createQuery(criteriaQuery);

		return speeches.getResultList();
	}

	private Predicate getPredicate(SpeechSearchCriteria speechSearchCriteria, Root<Speeches> root) {
		Predicate predicate = null;
		if(Objects.nonNull(speechSearchCriteria.getCategory())) {
			switch (speechSearchCriteria.getCategory()) {
			case AUTHOR:
				if(Objects.nonNull(speechSearchCriteria.getValue())) {
					predicate = this.criteriaBuilder.like(root.get("author").get("name"), "%" + speechSearchCriteria.getValue() + "%");
				}
				break;
			case SUBJECT_AREA:
				if(Objects.nonNull(speechSearchCriteria.getValue())) {
					predicate = this.criteriaBuilder.like(root.get("subject_area"), "%" + speechSearchCriteria.getValue() + "%");
				}
				break;
			case SNIPPETS:
				if(Objects.nonNull(speechSearchCriteria.getValue())) {
					predicate = this.criteriaBuilder.like(root.get("content"), "%" + speechSearchCriteria.getValue() + "%");
				}
								break;
			case DATE_RANGE:
				if(Objects.nonNull(speechSearchCriteria.getDate_from()) && Objects.nonNull(speechSearchCriteria.getDate_to())) {
					predicate = this.criteriaBuilder.between(root.get("date"), speechSearchCriteria.getDate_from(), speechSearchCriteria.getDate_to());
				}
				break;
			}
		}
		return predicate;
	}
}
