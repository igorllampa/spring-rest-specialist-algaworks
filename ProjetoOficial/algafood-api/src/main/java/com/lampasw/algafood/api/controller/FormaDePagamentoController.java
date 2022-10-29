package com.lampasw.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.api.assembler.FormaDePagamentoInputDisassembler;
import com.lampasw.algafood.api.assembler.FormaDePagamentoModelAssembler;
import com.lampasw.algafood.api.model.FormaDePagamentoModel;
import com.lampasw.algafood.api.model.input.FormaDePagamentoInput;
import com.lampasw.algafood.domain.model.FormaDePagamento;
import com.lampasw.algafood.domain.repository.FormaDePagamentoRepository;
import com.lampasw.algafood.domain.service.CadastroFormaDePagamento;

@RestController
@RequestMapping("formas-pagamento")
public class FormaDePagamentoController {

	@Autowired
	FormaDePagamentoRepository formaDePagamentoRepository;
	
	@Autowired
	CadastroFormaDePagamento cadastroFormaDePagamento;
		
	@Autowired
	FormaDePagamentoModelAssembler formaDePagamentoModelAssembler;
	
	@Autowired
	FormaDePagamentoInputDisassembler formaDePagamentoInputDisassembler;
	
	@GetMapping
	public List<FormaDePagamentoModel> listar(){
		List<FormaDePagamento> formasDePagamento = formaDePagamentoRepository.findAll();
		return formaDePagamentoModelAssembler.toCollectionModel(formasDePagamento);
	}
	
	@GetMapping("/{formaDePagamentoId}")
	public FormaDePagamentoModel buscar(@PathVariable Long formaDePagamentoId) {
		FormaDePagamento formaDePagamento = cadastroFormaDePagamento.buscarOuFalhar(formaDePagamentoId);
		
		return formaDePagamentoModelAssembler.toModel(formaDePagamento);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaDePagamentoModel adicionar(@RequestBody FormaDePagamentoInput formaDePagamentoInput) {
		
		FormaDePagamento formaDePagamento = formaDePagamentoInputDisassembler.toDomainObject(formaDePagamentoInput);
		
		formaDePagamento = cadastroFormaDePagamento.salvar(formaDePagamento);
		
		return formaDePagamentoModelAssembler.toModel(formaDePagamento);				
	}
	
	@PutMapping("/{formaDePagamentoId}")
	public FormaDePagamentoModel atualizar(@PathVariable Long formaDePagamentoId, @RequestBody FormaDePagamentoInput formaDePagamentoInput) {
		
		FormaDePagamento formaDePagamentoAtual = cadastroFormaDePagamento.buscarOuFalhar(formaDePagamentoId);
		
		formaDePagamentoInputDisassembler.copyToDomainObject(formaDePagamentoInput, formaDePagamentoAtual);
		
		formaDePagamentoAtual = cadastroFormaDePagamento.salvar(formaDePagamentoAtual);
		
		return formaDePagamentoModelAssembler.toModel(formaDePagamentoAtual);
		
	}
	
	@DeleteMapping("/{formaDePagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaDePagamentoId) {
		cadastroFormaDePagamento.remover(formaDePagamentoId);
	}
}
