package com.assesment.speeches.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "shared_speeches", indexes = @Index(columnList = "speech_id, email", unique = true))
public class SharedSpeeches {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "speech_id", nullable = false)
	private Speeches speech;

	@NotNull
	@NotEmpty
	@Email
	@Column(name = "email")
	private String email;

	public SharedSpeeches() {
	}

	public SharedSpeeches(Speeches speech, @Email @NotNull @NotEmpty String email) {
		super();
		this.speech = speech;
		this.email = email;
	}

	public Speeches getSpeech() {
		return speech;
	}

	public void setSpeech(Speeches speech) {
		this.speech = speech;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}
}
