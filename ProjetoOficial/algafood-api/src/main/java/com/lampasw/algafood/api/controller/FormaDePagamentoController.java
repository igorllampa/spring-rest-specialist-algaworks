package com.lampasw.algafood.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.domain.model.FormaDePagamento;
import com.lampasw.algafood.domain.repository.FormaDePagamentoRepository;

@RestController
@RequestMapping("formas-pagamento")
public class FormaDePagamentoController {

	FormaDePagamentoRepository formaDePagamentoRepository;

	public FormaDePagamentoController(FormaDePagamentoRepository formaDePagamentoRepository) {		
		this.formaDePagamentoRepository = formaDePagamentoRepository;
	}
	
	@GetMapping
	public List<FormaDePagamento> listar(){
		return formaDePagamentoRepository.findAll();
	}	
}
