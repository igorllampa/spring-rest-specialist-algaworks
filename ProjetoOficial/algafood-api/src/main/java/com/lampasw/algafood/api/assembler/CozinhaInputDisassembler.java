package com.lampasw.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.model.input.CozinhaInput;
import com.lampasw.algafood.domain.model.Cozinha;

@Component
public class CozinhaInputDisassembler {

	@Autowired
	ModelMapper modelMapper;
	
	public Cozinha toDomainObject(CozinhaInput cozinhaInput) {							
		return modelMapper.map(cozinhaInput, Cozinha.class);		
	}
	
	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}	
}
