package com.lampasw.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.FormaDePagamento;
import com.lampasw.algafood.domain.repository.CozinhaRepository;
import com.lampasw.algafood.domain.repository.FormaDePagamentoRepository;

public class ConsultaFormaDePagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
					
		FormaDePagamentoRepository formaDePagamentoRepository = applicationContext.getBean(FormaDePagamentoRepository.class);
		
		FormaDePagamento forma1 = new FormaDePagamento();
		forma1.setDescricao("Cartão de Débito");
		formaDePagamentoRepository.adicionar(forma1);
		
		FormaDePagamento forma2 = new FormaDePagamento();
		forma2.setDescricao("Boleto Bancário");
		formaDePagamentoRepository.adicionar(forma2);
		
		for (FormaDePagamento forma : formaDePagamentoRepository.todas()) {
			System.out.printf("%d - %s \n", forma.getId(), forma.getDescricao());			
		}
								
		
		FormaDePagamento formaDePagamentoBusca = formaDePagamentoRepository.porId(1L);
		formaDePagamentoBusca.setDescricao(formaDePagamentoBusca.getDescricao()+ " - Updatede");
		formaDePagamentoRepository.adicionar(formaDePagamentoBusca);
		
		for(FormaDePagamento forma : formaDePagamentoRepository.todas()) {
			System.out.printf("%d - %s \n", forma.getId(), forma.getDescricao());
		}
		
		formaDePagamentoRepository.remover(formaDePagamentoRepository.porId(1L));
		
		for(FormaDePagamento forma : formaDePagamentoRepository.todas()) {
			System.out.printf("%d - %s \n", forma.getId(), forma.getDescricao());			
		}
}
	
}
