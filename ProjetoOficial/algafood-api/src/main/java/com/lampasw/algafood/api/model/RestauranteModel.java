package com.lampasw.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaModel cozinha;
	
	private String cozinhaNome;
	private Long cozinhaId;
	
	private String descricao;
}
