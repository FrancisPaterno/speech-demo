package com.assesment.speeches.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Entity
@Table(name = "speeches")
public class Speeches {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "content")
	private String content;

	@NotEmpty
	@Column(name = "subject_area")
	private String subject_area;

	@Column(name = "date")
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "politician_id", nullable = false)
	private Politicians politician;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Authors author;

	public Speeches() {
	}

	public Speeches(String content, @NotEmpty String subject_area, Authors author, LocalDate date,
			Politicians politician) {
		this.content = content;
		this.subject_area = subject_area;
		this.author = author;
		this.date = date;
		this.politician = politician;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject_area() {
		return subject_area;
	}

	public void setSubject_area(String subject_area) {
		this.subject_area = subject_area;
	}

	public Authors getAuthor() {
		return author;
	}

	public void setAuthor(Authors author) {
		this.author = author;
	}

	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Politicians getPolitician() {
		return politician;
	}

	public void setPolitician(Politicians politician) {
		this.politician = politician;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
