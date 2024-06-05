package com.lampasw.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Maria José")
	private String nome;	
	
	@ApiModelProperty(example = "maria@jose.com.br")
	private String email;	
}
