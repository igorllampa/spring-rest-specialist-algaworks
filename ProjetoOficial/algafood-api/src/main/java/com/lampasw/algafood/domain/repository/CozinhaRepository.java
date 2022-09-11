package com.lampasw.algafood.domain.repository;

import java.util.List;

import com.lampasw.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> todas();
	List<Cozinha> consultarPorNome(String nome);	
	Cozinha buscar (Long id);		
	Cozinha salvar (Cozinha cozinha);
	void remover(Long id);
}
