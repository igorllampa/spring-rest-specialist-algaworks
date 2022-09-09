package com.lampasw.algafood.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {

	private RestauranteRepository restauranteRepository;

	public RestauranteController(RestauranteRepository restauranteRepository) {		
		this.restauranteRepository = restauranteRepository;
	}
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.todos();
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteRepository.porId(restauranteId);
		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}
		
		return ResponseEntity.notFound().build();
	}
}
