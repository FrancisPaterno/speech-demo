package com.assesment.speeches.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assesment.speeches.model.Authors;

public interface AuthorRepository extends JpaRepository<Authors, Long> {

}
