package com.lampasw.algafood.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.domain.model.Permissao;
import com.lampasw.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping("permissoes")
public class PermissaoController {

	private PermissaoRepository permissaoRepository;

	public PermissaoController(PermissaoRepository permissaoRepository) {	
		this.permissaoRepository = permissaoRepository;
	}
	
	@GetMapping
	public List<Permissao> listar(){
		return permissaoRepository.todas();
	}
}
