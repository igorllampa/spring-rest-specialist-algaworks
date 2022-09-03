package com.lampasw.algafood.domain.repository;

import java.util.List;

import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> todas();
	Estado porId (Long id);
	Estado adicionar (Estado estado);
	void remover(Estado estado);
}
