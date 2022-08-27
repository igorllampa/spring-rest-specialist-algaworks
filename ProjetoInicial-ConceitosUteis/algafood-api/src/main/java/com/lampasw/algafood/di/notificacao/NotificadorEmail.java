package com.lampasw.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.di.modelo.Cliente;

@Profile("prod")
@Component
@TipoDoNotificador(NivelUrgencia.NORMAL)
public class NotificadorEmail implements Notificador {
		
	@Autowired
	NotificadorProperties notificadorProperties;	
		
	@Value("${parametro.impressora.modelo}")
	private String parametroImpressoraModelo;
	
	@Value("${parametro.impressora.porta}")
	private String parametroImpressoraPorta;
	
	public NotificadorEmail() {
		System.out.println("Notificado e-mail REAL!! Em produção.");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {		
		System.out.printf("Notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
		System.out.println("Com classe de config - emailHost: " + notificadorProperties.getHost());
		System.out.println("Com classe de confir - emailPort: " + notificadorProperties.getPorta());
		System.out.println("Direto na variável - Impressora Modelo: " + this.parametroImpressoraModelo);
		System.out.println("Direto na variável - Impressora Porta: " + this.parametroImpressoraPorta);
	}	
}
