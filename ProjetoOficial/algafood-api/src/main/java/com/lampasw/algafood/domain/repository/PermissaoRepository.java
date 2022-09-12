package com.lampasw.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lampasw.algafood.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	/*
	List<Permissao> todas();
	Permissao porId (Long id);
	Permissao adicionar (Permissao permissao);
	void remover(Permissao permissao);
	*/
}
