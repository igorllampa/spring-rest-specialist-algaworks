package com.lampasw.algafood.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.lampasw.algafood.domain.model.Endereco;
import com.lampasw.algafood.domain.model.FormaDePagamento;
import com.lampasw.algafood.domain.model.ItemPedido;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.model.StatusPedido;
import com.lampasw.algafood.domain.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;			
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	private StatusPedido status;		
	private Endereco enderecoEntrega;			
	private FormaDePagamento formaDePagamento;				
	private Restaurante restaurante;			
	private Usuario cliente;		
	private List<ItemPedido> itens;	
}
