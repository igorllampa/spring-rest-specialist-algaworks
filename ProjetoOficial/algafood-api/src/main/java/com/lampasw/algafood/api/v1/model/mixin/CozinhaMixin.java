package com.lampasw.algafood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lampasw.algafood.domain.model.Restaurante;

public class CozinhaMixin {

	@JsonIgnore	
	private List<Restaurante> restaurante = new ArrayList<>();
}
