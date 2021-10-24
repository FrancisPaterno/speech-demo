package com.assesment.speeches.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assesment.speeches.model.Speeches;

@Repository
public interface SpeechRepository extends JpaRepository<Speeches, Long>{

	@Query("SELECT s from Speeches s where s.politician.id=:pol_id")
	List<Speeches> getSpeechesByPolitician(@Param("pol_id") Long polId);
}
