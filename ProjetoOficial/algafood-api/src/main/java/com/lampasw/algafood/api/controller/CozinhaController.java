package com.lampasw.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.api.assembler.CozinhaInputDisassembler;
import com.lampasw.algafood.api.assembler.CozinhaModelAssembler;
import com.lampasw.algafood.api.model.CozinhaModel;
import com.lampasw.algafood.api.model.CozinhasXmlWrapper;
import com.lampasw.algafood.api.model.input.CozinhaInput;
import com.lampasw.algafood.api.openapi.controller.CozinhaControllerOpenApi;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.repository.CozinhaRepository;
import com.lampasw.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value="cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CozinhaRepository cozinhaRepository;//Use autowired or use injection with the class controller
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;//Use autowired or use injection with the class controller
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable){
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		List<CozinhaModel> cozinhasModel = cozinhaModelAssembler.toCollectionModel(cozinhasPage.getContent());
		
		Page<CozinhaModel> cozinhasModelPage = new PageImpl<>(cozinhasModel, pageable, cozinhasPage.getTotalElements());
		
		return cozinhasModelPage;
	}
	
	@GetMapping("/listar-por-nome")
	public List<CozinhaModel> buscarPorNome(@RequestParam String nome){
		List<Cozinha> cozinhas = cozinhaRepository.findByNomeContains(nome);
		
		return cozinhaModelAssembler.toCollectionModel(cozinhas);
	}
	
	@GetMapping("/exists-por-nome")
	public boolean checarExisteCozinha(@RequestParam String nome) {
		return cozinhaRepository.existsByNomeContaining(nome);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml(){
		return new CozinhasXmlWrapper(cozinhaRepository.findAll());
	}		
	
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		return cozinhaModelAssembler.toModel(cozinha);	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		
		cozinha = cadastroCozinha.salvar(cozinha);			
		
		return cozinhaModelAssembler.toModel(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@RequestBody @Valid CozinhaInput cozinhaInput, @PathVariable Long cozinhaId) {
				
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);	
		
		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		
		cadastroCozinha.salvar( cozinhaAtual );
		
		return cozinhaModelAssembler.toModel(cozinhaAtual);		
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {							
		cadastroCozinha.excluir(cozinhaId);		
	}
}
