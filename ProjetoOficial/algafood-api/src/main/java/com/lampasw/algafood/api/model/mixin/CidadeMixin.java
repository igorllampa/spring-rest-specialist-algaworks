package com.lampasw.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lampasw.algafood.domain.model.Estado;

public class CidadeMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Estado estado;
}
