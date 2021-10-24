package com.assesment.speeches.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="politicians")
public class Politicians {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Column(name="name", unique = true)
	private String name;

	@Email
	@NotEmpty
	@Column(name="email", unique = true)
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "politician")
	private Set<Speeches> speeches;

	public Politicians() {

	}

	public Politicians(@NotNull @NotEmpty String name, @NotEmpty @Email String email, Set<Speeches> speeches) {
		this.name = name;
		this.email = email;
		this.speeches = speeches;
	}

	public Politicians(@NotNull @NotEmpty String name, @NotEmpty String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Speeches> getSpeeches() {
		return speeches;
	}

	public void setSpeeches(Set<Speeches> speeches) {
		this.speeches = speeches;
	}

	public Long getId() {
		return id;
	}
}
