package com.lampasw.algafood.domain.listener;

import java.util.Random;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.domain.event.PedidoConfirmadoEvent;
import com.lampasw.algafood.domain.model.Pedido;

@Component
public class BonificacaoClientePedidoConfirmadoListener {

	@EventListener
	private void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();
		Random rand = new Random();
		int bonificacao = rand.nextInt();
		
		System.out.printf("O pedido %s do cliente %s gerou %d pontos de bonificação.", 
				pedido.getCodigo(), 
				pedido.getCliente().getNome(), 
				bonificacao);
	}
}