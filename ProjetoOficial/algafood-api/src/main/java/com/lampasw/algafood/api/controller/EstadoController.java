package com.lampasw.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.domain.exception.EntidadeEmUsoException;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.EstadoRepository;
import com.lampasw.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("estados")
public class EstadoController {

	private EstadoRepository estadoRepository;
	private CadastroEstadoService cadastroEstado;
	
	public EstadoController(EstadoRepository estadoRepository, CadastroEstadoService cadastroEstado) {
		this.estadoRepository = estadoRepository;//use @autowired or injection with controller
		this.cadastroEstado = cadastroEstado;
	}

	@GetMapping
	private List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<?> buscar(@PathVariable Long estadoId){
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if(estado.isPresent()) {
			return ResponseEntity.ok(estado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
		estado = cadastroEstado.salvar(estado);
		if (estado != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(estado);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();		
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
		
		Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
		if (estadoAtual.isPresent()) {
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			estado = cadastroEstado.salvar(estadoAtual.get());
			return ResponseEntity.ok(estado);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O estado não existe");
	}
	
	@DeleteMapping("/{estadoId}")	
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstado.remover(estadoId);
			return ResponseEntity.noContent().build();
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
}