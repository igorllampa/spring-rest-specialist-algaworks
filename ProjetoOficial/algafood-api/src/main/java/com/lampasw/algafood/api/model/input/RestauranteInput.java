package com.lampasw.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lampasw.algafood.core.validation.Multiplo;
import com.lampasw.algafood.core.validation.TaxaFrete;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {

	@NotBlank
	private String nome;
		
	@TaxaFrete
	@Multiplo(numero = 5)
	@NotNull
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull	
	private CozinhaIdInput cozinha;
}
