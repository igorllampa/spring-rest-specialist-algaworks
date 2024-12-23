package com.lampasw.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@ApiModelProperty(example = "15555-000", required = true)
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "Av. Paulista", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "123", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "Bloco C")
	private String complemento;
		
	@ApiModelProperty(example = "Barra Funda", required = true)
	@NotBlank
	private String bairro;
		
	@Valid
	@NotNull
	private CidadeIdInput cidade;	
}
