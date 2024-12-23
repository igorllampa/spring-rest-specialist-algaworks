package com.lampasw.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lampasw.algafood.api.v1.model.mixin.CidadeMixin;
import com.lampasw.algafood.api.v1.model.mixin.CozinhaMixin;
import com.lampasw.algafood.domain.model.Cidade;
import com.lampasw.algafood.domain.model.Cozinha;

@Component
public class JacksonMixinModel extends SimpleModule {
	
	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModel() {	
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}
}
