package com.lampasw.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.model.input.CozinhaInput;
import com.lampasw.algafood.api.model.input.EstadoInput;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Estado;

@Component
public class EstadoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Estado toDomainObject(EstadoInput estadoInput) {							
		return modelMapper.map(estadoInput, Estado.class);		
	}
	
	public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}	
}
