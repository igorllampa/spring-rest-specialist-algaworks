package com.lampasw.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.v1.AlgaLinks;
import com.lampasw.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.lampasw.algafood.api.v1.model.FotoProdutoModel;
import com.lampasw.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}

	public FotoProdutoModel toModel(FotoProduto foto) {

		FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);

		fotoProdutoModel.add(algaLinks.linkToFotoProduto(foto.getRestauranteId(), foto.getProduto().getId()));

		fotoProdutoModel.add(algaLinks.linkToProduto(foto.getRestauranteId(), foto.getProduto().getId(), "produto"));

		return fotoProdutoModel;
	}

}
