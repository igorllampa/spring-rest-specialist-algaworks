package com.lampasw.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class PedidoModel extends RepresentationModel<PedidoModel> {
		
	@ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String codigo;
	
	@ApiModelProperty(example = "10.00")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "15.00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "25.00")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CRIADO")
	private String status;	
	
	@ApiModelProperty(example = "2024-06-05T20:34:04Z")
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(example = "2024-06-05T20:35:04Z")	
	private OffsetDateTime dataConfirmacao;
	
	@ApiModelProperty(example = "2024-06-05T20:45:01Z")
	private OffsetDateTime dataEntrega;
	
	@ApiModelProperty(example = "2024-06-05T20:37:04Z")
	private OffsetDateTime dataCancelamento;
		
	private RestauranteResumoModel restaurante;
		
	private UsuarioModel cliente;
		
	private FormaDePagamentoModel formaDePagamento;
		
	private EnderecoModel enderecoEntrega;
		
	private List<ItemPedidoModel> itens;
	
	private RestauranteApenasNomeModel restauranteNome;
}