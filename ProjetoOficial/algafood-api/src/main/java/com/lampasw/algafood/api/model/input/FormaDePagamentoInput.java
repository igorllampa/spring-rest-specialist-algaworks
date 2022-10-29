package com.lampasw.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaDePagamentoInput {

	@NotBlank
	private String descricao;
}
