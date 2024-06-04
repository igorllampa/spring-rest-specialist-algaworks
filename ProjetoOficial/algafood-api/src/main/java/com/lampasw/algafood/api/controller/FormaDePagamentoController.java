package com.lampasw.algafood.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.lampasw.algafood.api.assembler.FormaDePagamentoInputDisassembler;
import com.lampasw.algafood.api.assembler.FormaDePagamentoModelAssembler;
import com.lampasw.algafood.api.model.FormaDePagamentoModel;
import com.lampasw.algafood.api.model.input.FormaDePagamentoInput;
import com.lampasw.algafood.api.openapi.controller.FormaDePagamentoControllerOpenApi;
import com.lampasw.algafood.domain.model.FormaDePagamento;
import com.lampasw.algafood.domain.repository.FormaDePagamentoRepository;
import com.lampasw.algafood.domain.service.CadastroFormaDePagamentoService;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaDePagamentoController implements FormaDePagamentoControllerOpenApi {

	@Autowired
	FormaDePagamentoRepository formaDePagamentoRepository;
	
	@Autowired
	CadastroFormaDePagamentoService cadastroFormaDePagamento;
		
	@Autowired
	FormaDePagamentoModelAssembler formaDePagamentoModelAssembler;
	
	@Autowired
	FormaDePagamentoInputDisassembler formaDePagamentoInputDisassembler;
	
	@GetMapping
	public ResponseEntity<List<FormaDePagamentoModel>> listar(ServletWebRequest request){
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		
		OffsetDateTime dataUltimaAtualizacao = formaDePagamentoRepository.getDataUltimaAtualizacao();
		
		if(dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if (request.checkNotModified(eTag)) {
			return null;
		}
		
		List<FormaDePagamento> formasDePagamento = formaDePagamentoRepository.findAll();
		List<FormaDePagamentoModel> formasDePagamentoModel = formaDePagamentoModelAssembler
				.toCollectionModel(formasDePagamento);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
				.eTag(eTag)
				.body(formasDePagamentoModel);
				
	}
	
	@GetMapping("/{formaDePagamentoId}")
	public ResponseEntity<FormaDePagamentoModel> buscar(ServletWebRequest request, @PathVariable Long formaDePagamentoId) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
  		
		String eTag = "0";
		
		OffsetDateTime dataAtualizacao = formaDePagamentoRepository.getDataAtualizacaoById(formaDePagamentoId);
		
		if(dataAtualizacao != null) {
			eTag = String.valueOf(dataAtualizacao.toEpochSecond());
		}
		
		if (request.checkNotModified(eTag)) {
			return null;
		}
				
		FormaDePagamento formaDePagamento = cadastroFormaDePagamento.buscarOuFalhar(formaDePagamentoId);
		
		FormaDePagamentoModel formaDePagamentoModel = formaDePagamentoModelAssembler.toModel(formaDePagamento);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(15, TimeUnit.DAYS))
				.body(formaDePagamentoModel);
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
