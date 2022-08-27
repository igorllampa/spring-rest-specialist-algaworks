package com.lampasw.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.lampasw.algafood.di.modelo.Cliente;

@Component
@TipoDoNotificador(NivelUrgencia.URGENTE)
public class NotificadorSms implements Notificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {		
		System.out.printf("Notificando %s através do telefone %s: %s\n", 
				cliente.getNome(), cliente.getTelefone(), mensagem);		
	}	
		
}
