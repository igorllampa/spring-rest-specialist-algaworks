package com.lampasw.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.model.input.RestauranteInput;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		
		//Manul process for mapping objects
//		Cozinha cozinha = new Cozinha();
//		cozinha.setId(restauranteInput.getCozinha().getId());
//		
//		Restaurante restaurante = new Restaurante();
//		restaurante.setNome(restauranteInput.getNome());
//		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
//		restaurante.setCozinha(cozinha);		
//		
		
		//Use modelmapper to automatize the process of mapping objects
		return modelMapper.map(restauranteInput, Restaurante.class);		
	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		//restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteInput, restaurante);
	}
}
