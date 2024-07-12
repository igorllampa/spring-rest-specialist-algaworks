package com.lampasw.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteResumoModel extends RepresentationModel<RestauranteResumoModel> {

	@ApiModelProperty(example = "0")
	private Long id;
	
	@ApiModelProperty(example = "Restaurante Tudo Blz!")
	private String nome;	
}
