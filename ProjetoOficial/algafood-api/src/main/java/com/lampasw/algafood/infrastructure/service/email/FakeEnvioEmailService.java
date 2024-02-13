package com.lampasw.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;

import com.lampasw.algafood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService implements EnvioEmailService{

	@Autowired
	private Configuration freemarkerConfig;
	
	@Override
	public void enviar(Mensagem mensagem) {
        String corpo = processarTemplate(mensagem, freemarkerConfig);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
	}

}
