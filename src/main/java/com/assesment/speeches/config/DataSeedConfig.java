package com.assesment.speeches.config;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assesment.speeches.model.Authors;
import com.assesment.speeches.model.Politicians;
import com.assesment.speeches.model.Speeches;
import com.assesment.speeches.repository.AuthorRepository;
import com.assesment.speeches.repository.PoliticianRepository;
import com.assesment.speeches.repository.SpeechRepository;

@Configuration
public class DataSeedConfig {

	@Bean
	CommandLineRunner commandLineRunner(PoliticianRepository politicianRepository, SpeechRepository speechRepository, AuthorRepository authorRepository) {
		return args->{
			Authors auth1 = new Authors("KC Conception");
			Authors auth2 = new Authors("Francis Paterno");
			Authors auth3 = new Authors("Naomi Paterno");
			Authors auth4 = new Authors("Boggart Paterno");
			authorRepository.saveAll(List.of(auth1, auth2, auth3, auth4));

			Politicians pol1 = new  Politicians("Francis Paterno", "francis.paterno@gmail.com");
			Politicians pol2 = new  Politicians("Naomi Paterno", "Naomi.paterno@gmail.com");
			Politicians pol3 = new  Politicians("Boggart Paterno", "boggart.paterno@gmail.com");
			politicianRepository.saveAllAndFlush(List.of(pol1,pol2,pol3));

			Speeches s1 = new Speeches("This is Francis Paterno and this is my first speech content", "First Speech Subject", auth2, LocalDate.of(2021,10,1),pol1);
			Speeches s2 = new Speeches("This is Francis Paterno and this is my second speech content", "Second Subject Area", auth2, LocalDate.of(2021,10,5), pol1);
			Speeches s3 = new Speeches("This is Francis Paterno and this is my third speech content", "Third Subject Area", auth1, LocalDate.of(2021,10,15), pol1);
			Speeches s4 = new Speeches("This is Naomi Paterno and this is my first speech content", "First Campaign Speech", auth3, LocalDate.of(2021,10,7), pol2);
			Speeches s5 = new Speeches("This is Naomi Therese Paterno and this is my second speech content", "New Political Campaign", auth1, LocalDate.of(2021,10,10), pol2);
			Speeches s6 = new Speeches("Hi!, This is Francis Paterno and this is my last speech content", "Extro-Speech", auth3, LocalDate.of(2021,10,5), pol2);
			Speeches s7 = new Speeches("This is Boggart Paterno and this is my introductory speech content", "My First speech", auth1, LocalDate.of(2021,10,3), pol3);
			Speeches s8 = new Speeches("This is Boggart Paterno and this is my Climax speech content", "Climax", auth4, LocalDate.of(2021,10,5), pol3);
			Speeches s9 = new Speeches("This is Boggart Paterno and this is my Last speech content", "My outro speech", auth4, LocalDate.of(2021,10,20), pol3);
			Speeches s10 = new Speeches("This is Boggart Paterno and this is my original speech content", "Genuine Subject Area", auth4, LocalDate.of(2021,10,13), pol3);
			speechRepository.saveAll(Set.of(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10));
		};
	}
}
