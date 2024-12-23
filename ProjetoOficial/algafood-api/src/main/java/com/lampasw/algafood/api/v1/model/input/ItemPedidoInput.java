package com.lampasw.algafood.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@ApiModelProperty(example = "0", required = true)
	@NotNull
	private Long produtoId;
	
	@ApiModelProperty(example = "5", required = true)
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	
	@ApiModelProperty(example = "Molho extra e com mais cebola")
	private String observacao;
}
