package com.lampasw.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lampasw.algafood.api.model.EnderecoModel;
import com.lampasw.algafood.api.model.input.ItemPedidoInput;
import com.lampasw.algafood.domain.model.Endereco;
import com.lampasw.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		
		var modelMapper = new ModelMapper();
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSource -> enderecoSource.getCidade().getEstado().getNome(),
				(enderecoModelDestination, value) -> enderecoModelDestination.getCidade().setEstado(value));
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
	    .addMappings(mapper -> mapper.skip(ItemPedido::setId));  
			
		return modelMapper;
	}
}
