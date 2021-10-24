package com.assesment.speeches.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assesment.speeches.model.Politicians;

@Repository
public interface PoliticianRepository extends JpaRepository<Politicians, Long> {

}
