package com.assesment.speeches.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assesment.speeches.model.SharedSpeeches;

@Repository
public interface SharedSpeechRepository extends JpaRepository<SharedSpeeches, Long>{

	@Query("SELECT s FROM SharedSpeeches s WHERE s.speech.politician.id=:pol_id")
	List<SharedSpeeches> getSharedSpeeches(@Param("pol_id") Long id);
}
