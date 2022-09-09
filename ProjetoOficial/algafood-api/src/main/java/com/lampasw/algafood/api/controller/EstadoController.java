package com.lampasw.algafood.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.EstadoRepository;

@RestController
@RequestMapping("estados")
public class EstadoController {

	private EstadoRepository estadoRepository;
	
	public EstadoController(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;//use @autowired or injection with controller
	}

	@GetMapping
	private List<Estado> listar(){
		return estadoRepository.todos();
	}
}