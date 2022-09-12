package com.lampasw.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lampasw.algafood.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	/*
	List<Estado> listar();
	Estado buscar (Long id);
	Estado salvar (Estado estado);
	void remover(Long id);
	*/
}
