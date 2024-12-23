package com.lampasw.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel>{
	
	@ApiModelProperty(example = "1")
	private Long produtoId;
	
	@ApiModelProperty(example = "X-Burguer")
	private String produtoNome;
	
	@ApiModelProperty(example = "2")
	private Integer quantidade;
	
	@ApiModelProperty(example = "15.00")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(example = "30.00")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(example = "Sem pimenta e mostarda")
	private String observacao;			
}