package com.lampasw.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.model.RestauranteModel;
import com.lampasw.algafood.api.model.input.CozinhaIdInput;
import com.lampasw.algafood.api.model.input.RestauranteInput;
import com.lampasw.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteModel toModel(Restaurante restaurante) {
		//Manual mapping 
//		CozinhaModel cozinhaModel = new CozinhaModel();
//		cozinhaModel.setId(restaurante.getCozinha().getId());
//		cozinhaModel.setNome(restaurante.getCozinha().getNome());
//		
//		RestauranteModel restauranteModel = new RestauranteModel();			
//		restauranteModel.setId(restaurante.getId());
//		restauranteModel.setNome(restaurante.getNome());
//		restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
//		restauranteModel.setCozinha(cozinhaModel);
		
//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//				.addMapping(Restaurante::getNome, RestauranteModel::setDescricao);
		
		//Using ModelMapper to automatize the process of mapping objects
		return modelMapper.map(restaurante, RestauranteModel.class);			
	}
	
	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes){
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
	
	public RestauranteInput toInputModel(Restaurante restauranteAtual) {
		CozinhaIdInput cozinha = new CozinhaIdInput();
		cozinha.setId(restauranteAtual.getCozinha().getId());
		RestauranteInput restauranteInput = new RestauranteInput();
		restauranteInput.setNome(restauranteAtual.getNome());
		restauranteInput.setTaxaFrete(restauranteAtual.getTaxaFrete());
		restauranteInput.setCozinha(cozinha);
		return restauranteInput;
	}
	
}
