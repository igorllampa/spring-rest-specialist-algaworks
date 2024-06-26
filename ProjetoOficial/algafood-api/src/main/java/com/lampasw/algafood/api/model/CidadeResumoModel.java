package com.lampasw.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {
	
	@ApiModelProperty(example = "0")
	private Long id;	
	
	@ApiModelProperty(example = "São Paulo")
	private String nome;		
	
	@ApiModelProperty(example = "São Paulo")
	private String estado;	
}
