package com.lampasw.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@ApiModelProperty(example = "X-tudo", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "Hamburguer artesanal e molho especial", required = true)
	@NotBlank
	private String descricao;		
	
	@ApiModelProperty(example = "10.90", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true", required = true)
	@NotNull
	private Boolean ativo;	
}