package com.lampasw.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoModel extends RepresentationModel<ProdutoModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "X-egg")
	private String nome;

	@ApiModelProperty(example = "Pão, ovo, hamburguer e queijo")
	private String descricao;

	@ApiModelProperty(example = "10.99")
	private BigDecimal preco;

	@ApiModelProperty(example = "true")
	private Boolean ativo;
}
