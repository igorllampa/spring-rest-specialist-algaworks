package com.lampasw.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.di.modelo.Cliente;

@Profile("dev")
@Component
@TipoDoNotificador(NivelUrgencia.NORMAL)
public class NotificadorEmailMock implements Notificador {
	
	@Autowired
	NotificadorProperties notificadorProperties;
	
	@Value("${parametro.impressora.modelo}")
	private String parametroImpressoraModelo;
	
	@Value("${parametro.impressora.porta}")
	private String parametroImpressoraPorta;
	
	public NotificadorEmailMock() {
		System.out.println("Notificador e-mail MOCK: em ambiente DEV");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {		
		System.out.printf("Notificação seria enviada para %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);		
		
		System.out.println("Com classe de config - emailHost: " + notificadorProperties.getHost());
		System.out.println("Com classe de confir - emailPort: " + notificadorProperties.getPorta());
		System.out.println("Direto na variável - Impressora Modelo: " + this.parametroImpressoraModelo);
		System.out.println("Direto na variável - Impressora Porta: " + this.parametroImpressoraPorta);
	}	
		
}
