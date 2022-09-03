package com.lampasw.algafood.domain.repository;

import java.util.List;

import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.FormaDePagamento;

public interface FormaDePagamentoRepository {

	List<FormaDePagamento> todas();
	FormaDePagamento porId (Long id);
	FormaDePagamento adicionar (FormaDePagamento formaDePagamento);
	void remover(FormaDePagamento formaDePagamento);
}
