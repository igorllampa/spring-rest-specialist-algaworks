package com.lampasw.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {
	
	@ApiModelProperty(example = "38400-000")
	private String cep;	
	
	@ApiModelProperty(example = "Av. dos Bandeirantes")
	private String logradouro;	
	
	@ApiModelProperty(example = "456")
	private String numero;	
	
	@ApiModelProperty(example = "Bloco D")
	private String complemento;		
	
	@ApiModelProperty(example = "Alphaville")
	private String bairro;		
		
	private CidadeResumoModel cidade;
}
