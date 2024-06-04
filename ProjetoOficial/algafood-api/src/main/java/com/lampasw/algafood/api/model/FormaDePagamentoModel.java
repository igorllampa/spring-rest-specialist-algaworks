package com.lampasw.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaDePagamentoModel {

	@ApiModelProperty(value = "ID de uma forma de pagamento", example = "1")	
	private Long id;
	
	@ApiModelProperty(example = "Pix")
	private String descricao;
}
