package com.lampasw.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInput extends UsuarioInput {
	
	@ApiModelProperty(example = "awFASa*Az78", required = true)
	@NotBlank
	private String senha;		
}