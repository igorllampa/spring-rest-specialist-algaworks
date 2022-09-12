package com.lampasw.algafood.jpa;

import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.FormaDePagamento;
import com.lampasw.algafood.domain.repository.FormaDePagamentoRepository;

public class ConsultaFormaDePagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
					
		FormaDePagamentoRepository formaDePagamentoRepository = applicationContext.getBean(FormaDePagamentoRepository.class);
		
		FormaDePagamento forma1 = new FormaDePagamento();
		forma1.setDescricao("Cartão de Débito");
		formaDePagamentoRepository.save(forma1);
		
		FormaDePagamento forma2 = new FormaDePagamento();
		forma2.setDescricao("Boleto Bancário");
		formaDePagamentoRepository.save(forma2);
		
		for (FormaDePagamento forma : formaDePagamentoRepository.findAll()) {
			System.out.printf("%d - %s \n", forma.getId(), forma.getDescricao());			
		}
								
		
		Optional<FormaDePagamento> formaDePagamentoBusca = formaDePagamentoRepository.findById(1L);
		formaDePagamentoBusca.get().setDescricao(formaDePagamentoBusca.get().getDescricao()+ " - Updatede");
		formaDePagamentoRepository.save(formaDePagamentoBusca.get());
		
		for(FormaDePagamento forma : formaDePagamentoRepository.findAll()) {
			System.out.printf("%d - %s \n", forma.getId(), forma.getDescricao());
		}
		
		formaDePagamentoRepository.deleteById(1L);
		
		for(FormaDePagamento forma : formaDePagamentoRepository.findAll()) {
			System.out.printf("%d - %s \n", forma.getId(), forma.getDescricao());			
		}
	}	
}
