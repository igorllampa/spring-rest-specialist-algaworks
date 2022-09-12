package com.lampasw.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.lampasw.algafood.api.model.CozinhasXmlWrapper;
import com.lampasw.algafood.domain.exception.EntidadeEmUsoException;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.repository.CozinhaRepository;
import com.lampasw.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value="cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;//Use autowired or use injection with the class controller
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;//Use autowired or use injection with the class controller
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
	}
	
//	@GetMapping("/listar-por-nome")
//	public List<Cozinha> buscarPorNome(@RequestParam String nome){
//		return cozinhaRepository.consultarPorNome(nome);
//	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml(){
		return new CozinhasXmlWrapper(cozinhaRepository.findAll());
	}		
	
	@GetMapping("/{cozinhaId}")
	//@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		
		//Exemplo de utilizacao de cabecalho de redirecionamento
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//		return ResponseEntity
//				.status(HttpStatus.NOT_FOUND)
//				.headers(httpHeaders)
//				.body(cozinha);
				
		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		}
		
		return ResponseEntity.notFound().build();
									
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long cozinhaId) {
				
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
		if (cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
			cozinha = cadastroCozinha.salvar(cozinhaAtual.get());
			return ResponseEntity.ok(cozinha);			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
		try {					
			cadastroCozinha.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
