package com.lampasw.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.lampasw.algafood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> consultarComJpqlDinamico(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> consultarComCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}