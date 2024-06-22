package com.lampasw.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@ApiModelProperty(example = "José de Fátima", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "josefatima@l2.com.br", required = true)
	@NotBlank
	@Email
	private String email;		
}