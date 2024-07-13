package com.lampasw.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

	@ApiModelProperty(example = "0")
	// @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private Long id;

	@ApiModelProperty(example = "Monte Alto Food")
	// @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;

	@ApiModelProperty(example = "5.80")
	// @JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;

	// @JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;

	@ApiModelProperty(example = "true")
	private Boolean ativo;

	@ApiModelProperty(example = "true")
	private Boolean aberto;

	private EnderecoModel endereco;
}
