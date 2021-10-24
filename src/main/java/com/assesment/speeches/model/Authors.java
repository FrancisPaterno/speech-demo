package com.assesment.speeches.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authors")
public class Authors {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Column(name = "name")
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "author")
	private Set<Speeches> speeches;

	public Authors() {
	}

	public Authors(@NotNull @NotEmpty String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
