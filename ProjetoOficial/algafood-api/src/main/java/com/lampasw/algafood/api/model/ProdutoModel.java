package com.lampasw.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "X-egg")
	private String nome;
	
	@ApiModelProperty(example = "Pão, ovo, hamburguer e queijo")
	private String descricao;
	
	@ApiModelProperty(example = "10.99")
	private BigDecimal preco;	
	
	@ApiModelProperty(example = "true")
	private Boolean ativo;
}
