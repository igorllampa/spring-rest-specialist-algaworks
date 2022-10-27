package com.lampasw.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.model.CidadeModel;
import com.lampasw.algafood.api.model.CozinhaModel;
import com.lampasw.algafood.api.model.EstadoModel;
import com.lampasw.algafood.api.model.RestauranteModel;
import com.lampasw.algafood.api.model.input.CozinhaIdInput;
import com.lampasw.algafood.api.model.input.RestauranteInput;
import com.lampasw.algafood.domain.model.Cidade;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.model.Restaurante;

@Component
public class CidadeModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeModel toModel(Cidade cidade) {
				
		return modelMapper.map(cidade, CidadeModel.class);			
	}
	
	public List<CidadeModel> toCollectionModel(List<Cidade> cidades){
		return cidades.stream()
				.map(cidade -> toModel(cidade))
				.collect(Collectors.toList());
	}	
}
