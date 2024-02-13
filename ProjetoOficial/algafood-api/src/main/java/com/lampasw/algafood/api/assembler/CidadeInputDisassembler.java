package com.lampasw.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.model.input.CidadeInput;
import com.lampasw.algafood.domain.model.Cidade;

@Component
public class CidadeInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInput cidadeInput) {							
		return modelMapper.map(cidadeInput, Cidade.class);		
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		modelMapper.map(cidadeInput, cidade);
	}	
}
