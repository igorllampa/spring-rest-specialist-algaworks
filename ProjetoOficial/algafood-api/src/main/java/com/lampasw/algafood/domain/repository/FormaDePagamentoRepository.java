package com.lampasw.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lampasw.algafood.domain.model.FormaDePagamento;

public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long> {

	/*
	List<FormaDePagamento> todas();
	FormaDePagamento porId (Long id);
	FormaDePagamento adicionar (FormaDePagamento formaDePagamento);
	void remover(FormaDePagamento formaDePagamento);
	*/
}
