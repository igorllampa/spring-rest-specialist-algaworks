package com.lampasw.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.model.FormaDePagamentoModel;
import com.lampasw.algafood.domain.model.FormaDePagamento;

@Component
public class FormaDePagamentoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaDePagamentoModel toModel(FormaDePagamento formaDePagamento) {
		return modelMapper.map(formaDePagamento, FormaDePagamentoModel.class);
	}
	
	public List<FormaDePagamentoModel> toCollectionModel(List<FormaDePagamento> formasDePagamento){
		return formasDePagamento.stream()
			.map(formaDePagamento -> toModel(formaDePagamento))
			.collect(Collectors.toList());
	}
	
}
