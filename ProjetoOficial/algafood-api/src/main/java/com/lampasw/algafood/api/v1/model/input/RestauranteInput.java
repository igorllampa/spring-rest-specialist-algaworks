package com.lampasw.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lampasw.algafood.core.validation.Multiplo;
import com.lampasw.algafood.core.validation.TaxaFrete;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {

	@ApiModelProperty(example = "Monte Altvs Fast Food", required = true)
	@NotBlank	
	private String nome;
		
	@ApiModelProperty(example = "12.80", required = true)
	@TaxaFrete
	@Multiplo(numero = 5)
	@NotNull
	private BigDecimal taxaFrete;
		
	@Valid
	@NotNull	
	private CozinhaIdInput cozinha;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
}
