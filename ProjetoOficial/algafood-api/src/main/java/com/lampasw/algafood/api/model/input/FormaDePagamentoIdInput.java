package com.lampasw.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaDePagamentoIdInput {

	@NotNull
	private Long id;
}
