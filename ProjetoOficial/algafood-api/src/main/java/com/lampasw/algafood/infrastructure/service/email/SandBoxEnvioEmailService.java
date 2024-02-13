package com.lampasw.algafood.infrastructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.lampasw.algafood.core.email.EmailProperties;
import com.lampasw.algafood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;

public class SandBoxEnvioEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;
		
	@Autowired
	private Configuration freemarkerConfig;
	
	@Override
	public void enviar(Mensagem mensagem) {
		
		try {
			String corpo = processarTemplate(mensagem, freemarkerConfig);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(emailProperties.getSandbox().getDestinatario());
			helper.setSubject(mensagem.getAssunto());
			helper.setText(corpo, true);
			
			
			mailSender.send(mimeMessage);
		}catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
		
	}
}
