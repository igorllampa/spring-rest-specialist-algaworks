package com.lampasw.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.di.notificacao.NivelUrgencia;
import com.lampasw.algafood.di.notificacao.Notificador;
import com.lampasw.algafood.di.notificacao.TipoDoNotificador;
import com.lampasw.algafood.di.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {

	@Autowired
	@TipoDoNotificador(NivelUrgencia.NORMAL)
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		System.out.println("Cliente: " + event.getCliente().getNome());
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
	}	
}
