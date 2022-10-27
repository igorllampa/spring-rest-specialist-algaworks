package com.lampasw.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.model.CozinhaModel;
import com.lampasw.algafood.api.model.EstadoModel;
import com.lampasw.algafood.api.model.RestauranteModel;
import com.lampasw.algafood.api.model.input.CozinhaIdInput;
import com.lampasw.algafood.api.model.input.RestauranteInput;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.model.Restaurante;

@Component
public class EstadoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoModel toModel(Estado estado) {
				
		return modelMapper.map(estado, EstadoModel.class);			
	}
	
	public List<EstadoModel> toCollectionModel(List<Estado> estados){
		return estados.stream()
				.map(estado -> toModel(estado))
				.collect(Collectors.toList());
	}	
}
