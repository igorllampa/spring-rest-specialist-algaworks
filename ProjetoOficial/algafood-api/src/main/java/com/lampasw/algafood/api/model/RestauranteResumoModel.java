package com.lampasw.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoModel {

	@ApiModelProperty(example = "0")
	private Long id;
	
	@ApiModelProperty(example = "Restaurante Tudo Blz!")
	private String nome;	
}
