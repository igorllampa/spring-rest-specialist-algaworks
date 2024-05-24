package com.lampasw.algafood.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lampasw.algafood.domain.model.FormaDePagamento;

public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long> {

	@Query("select max(dataAtualizacao) from FormaDePagamento")
	OffsetDateTime getDataUltimaAtualizacao();
	/*
	List<FormaDePagamento> todas();
	FormaDePagamento porId (Long id);
	FormaDePagamento adicionar (FormaDePagamento formaDePagamento);
	void remover(FormaDePagamento formaDePagamento);
	*/
}
