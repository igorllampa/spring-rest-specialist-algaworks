package com.lampasw.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeResumoModel extends RepresentationModel<CidadeResumoModel> {
	
	@ApiModelProperty(example = "0")
	private Long id;	
	
	@ApiModelProperty(example = "São Paulo")
	private String nome;		
	
	@ApiModelProperty(example = "São Paulo")
	private String estado;	
}
