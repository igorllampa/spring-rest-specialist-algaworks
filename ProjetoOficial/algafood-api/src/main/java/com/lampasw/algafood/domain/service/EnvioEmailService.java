package com.lampasw.algafood.domain.service;

import java.util.Map;
import java.util.Set;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lampasw.algafood.infrastructure.service.email.EmailException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

public interface EnvioEmailService {
	
	
	void enviar(Mensagem mensagem);
		
	default String processarTemplate(Mensagem mensagem, Configuration freemarkerConfig) {
		
		try {
			Template corpoTemplate = freemarkerConfig.getTemplate(mensagem.getCorpoTemplate());		
			return FreeMarkerTemplateUtils.processTemplateIntoString(corpoTemplate, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException( "Não foi possível montar o template do e-mail", e);
		}
	}	
	
	@Getter
	@Builder
	class Mensagem{
		
		@Singular
		@NonNull
		private Set<String> destinatarios;
		
		@NonNull
		private String assunto;
		
		@NonNull
		private String corpoTemplate;		
		
		@Singular("variavel")
		private Map<String, Object> variaveis;
	}
}
