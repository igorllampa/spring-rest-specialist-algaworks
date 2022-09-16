package com.lampasw.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lampasw.algafood.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	List<Cozinha> findByNomeContains(String nome);	
	
	boolean existsByNomeContaining(String nome);
	
	/*
	//This methods were developed without Spring Data Jpa, using directly JPQL at the implementation
	List<Cozinha> todas();		
	Cozinha buscar (Long id);		
	Cozinha salvar (Cozinha cozinha);
	void remover(Long id);
	*/
}