package com.lampasw.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.type.TrueFalseType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
		
	@ApiModelProperty(example = "Monte Alto")
	@NotBlank
	private String nome;
	
	@Valid	
	@NotNull	
	private EstadoIdInput estado;
}
