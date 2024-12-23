package com.lampasw.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasDePagamento")
@Getter
@Setter
public class FormaDePagamentoModel  extends RepresentationModel<FormaDePagamentoModel> {

	@ApiModelProperty(value = "ID de uma forma de pagamento", example = "1")	
	private Long id;
	
	@ApiModelProperty(example = "Pix")
	private String descricao;
}
