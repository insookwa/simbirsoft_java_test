package com.simbirsoft.simbirsoftjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simbirsoft.simbirsoftjava.model.SearchesModel;

@Repository
public interface SearchesRepository extends JpaRepository<SearchesModel, Long> {
	
}
