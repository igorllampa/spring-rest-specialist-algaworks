package com.lampasw.algafood.domain.repository;

import java.util.List;

import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> todos();
	Restaurante porId (Long id);
	Restaurante adicionar (Restaurante restaurante);
	void remover(Restaurante restaurante);
}
