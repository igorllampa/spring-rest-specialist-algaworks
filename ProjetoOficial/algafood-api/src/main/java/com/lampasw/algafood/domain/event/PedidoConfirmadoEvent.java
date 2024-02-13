package com.lampasw.algafood.domain.event;

import com.lampasw.algafood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {
	
	private Pedido pedido;
	

}
