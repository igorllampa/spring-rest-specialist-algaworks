package com.lampasw.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeModel extends RepresentationModel<CidadeModel>{

	@ApiModelProperty(value = "ID da cidade", example = "1")
	private Long id;

	@ApiModelProperty(example = "Monte Alto")
	private String nome;

	private EstadoModel estado;
}
