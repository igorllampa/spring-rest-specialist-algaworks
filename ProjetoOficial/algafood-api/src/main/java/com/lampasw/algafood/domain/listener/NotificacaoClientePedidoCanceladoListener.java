package com.lampasw.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.lampasw.algafood.domain.event.PedidoCanceladoEvent;
import com.lampasw.algafood.domain.model.Pedido;
import com.lampasw.algafood.domain.service.EnvioEmailService;
import com.lampasw.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener
	private void aoCancelarPedido(PedidoCanceladoEvent event) {
	
		Pedido pedido = event.getPedido();
        
        var mensagem = Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
                .corpoTemplate("pedido-cancelado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmail.enviar(mensagem);
	}
}
