package com.assesment.speeches.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class SpeechSearchCriteria {

	@NotNull
	private SearchCategory category;

	private String value;

	private LocalDate date_from;

	private LocalDate date_to;

	public SearchCategory getCategory() {
		return category;
	}

	public void setCategory(SearchCategory category) {
		this.category = category;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDate getDate_from() {
		return date_from;
	}

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public void setDate_from(LocalDate date_from) {
		this.date_from = date_from;
	}

	public LocalDate getDate_to() {
		return date_to;
	}

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public void setDate_to(LocalDate date_to) {
		this.date_to = date_to;
	}
}
