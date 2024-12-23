package com.lampasw.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.v1.AlgaLinks;
import com.lampasw.algafood.api.v1.controller.RestauranteController;
import com.lampasw.algafood.api.v1.model.RestauranteModel;
import com.lampasw.algafood.api.v1.model.input.CozinhaIdInput;
import com.lampasw.algafood.api.v1.model.input.RestauranteInput;
import com.lampasw.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
	    RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
	    modelMapper.map(restaurante, restauranteModel);
	    
	    restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
	    
	    if (restaurante.ativacaoPermitida()) {
	        restauranteModel.add(
	                algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
	    }

	    if (restaurante.inativacaoPermitida()) {
	        restauranteModel.add(
	                algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
	    }

	    if (restaurante.aberturaPermitida()) {
	        restauranteModel.add(
	                algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
	    }

	    if (restaurante.fechamentoPermitido()) {
	        restauranteModel.add(
	                algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
	    }
	    
	    restauranteModel.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));
	    
	    restauranteModel.getCozinha().add(
	            algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
	    
	    if (restauranteModel.getEndereco() != null 
	            && restauranteModel.getEndereco().getCidade() != null) {
	        restauranteModel.getEndereco().getCidade().add(
	                algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
	    }
	    
	    restauranteModel.add(algaLinks.linkToRestauranteFormasDePagamento(restaurante.getId(), 
	            "formas-pagamento"));
	    
	    restauranteModel.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(), 
	            "responsaveis"));
	    
	    return restauranteModel;
	}
	
	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
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
