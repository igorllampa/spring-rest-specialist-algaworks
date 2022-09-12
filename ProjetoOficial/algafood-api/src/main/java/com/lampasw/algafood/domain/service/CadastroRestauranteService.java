package com.lampasw.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.repository.CozinhaRepository;
import com.lampasw.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("A cozinha nro %d não existe. Por favor, verifique.", cozinhaId)));
				
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);								
	}
}
