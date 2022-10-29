package com.lampasw.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.model.input.FormaDePagamentoInput;
import com.lampasw.algafood.domain.model.FormaDePagamento;

@Component
public class FormaDePagamentoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaDePagamento toDomainObject(FormaDePagamentoInput formaDePagamentoInput) {
		return modelMapper.map(formaDePagamentoInput, FormaDePagamento.class);
	}	
	
	public void copyToDomainObject(FormaDePagamentoInput formaDePagamentoInput, FormaDePagamento formaDePagamento) {
		modelMapper.map(formaDePagamentoInput, formaDePagamento);		
	}
}
