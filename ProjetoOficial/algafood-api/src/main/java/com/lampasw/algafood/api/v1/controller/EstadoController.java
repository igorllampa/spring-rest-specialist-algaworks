package com.lampasw.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.lampasw.algafood.api.v1.assembler.EstadoModelAssembler;
import com.lampasw.algafood.api.v1.model.EstadoModel;
import com.lampasw.algafood.api.v1.model.input.EstadoInput;
import com.lampasw.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.EstadoRepository;
import com.lampasw.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi{

	private EstadoRepository estadoRepository;
	private CadastroEstadoService cadastroEstado;
	private EstadoInputDisassembler estadoInputDisassembler;
	private EstadoModelAssembler estadoModelAssembler;
	
	public EstadoController(EstadoRepository estadoRepository, 
							CadastroEstadoService cadastroEstado,
							EstadoInputDisassembler estadoInputDisassembler,
							EstadoModelAssembler estadoModelAssembler) {
		this.estadoRepository = estadoRepository;//use @autowired or injection with constructor
		this.cadastroEstado = cadastroEstado;
		this.estadoInputDisassembler = estadoInputDisassembler;
		this.estadoModelAssembler = estadoModelAssembler;
	}

	@GetMapping
	public CollectionModel<EstadoModel> listar(){
		List<Estado> estados = estadoRepository.findAll();
		
		return estadoModelAssembler.toCollectionModel(estados);
	}
	
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId){
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		return estadoModelAssembler.toModel(estado);			
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput){
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		
		estado = cadastroEstado.salvar(estado);
		
		return estadoModelAssembler.toModel(estado);				
	}
	
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput){
		
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
		
		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		
		estadoAtual = cadastroEstado.salvar(estadoAtual);
		
		return estadoModelAssembler.toModel(estadoAtual);				
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {		
		cadastroEstado.remover(estadoId);				
	}
}