package com.lampasw.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")//Irá buscar no arquivo application.properties todos os campos
											 //com o prefixo notificador.email

public class NotificadorProperties {

	/**
	 * Host do Servidor de e-mail
	 */
	private String host;
	
	/**
	 * Porta do Servidor de e-mails
	 */
	private Integer porta = 25;
	
	public String getHost() {
		return host;
	}
	public Integer getPorta() {
		return porta;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
}
