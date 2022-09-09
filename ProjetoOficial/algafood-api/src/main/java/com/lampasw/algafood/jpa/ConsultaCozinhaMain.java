package com.lampasw.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Japonesa");
		cozinhaRepository.salvar(cozinha1);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Portuguesa");
		cozinhaRepository.salvar(cozinha2);
					
		List<Cozinha> cozinhas = cozinhaRepository.todas();
		
		for (Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
		}		
		
		Cozinha cozinhaBusca = cozinhaRepository.buscar(1L);
		System.out.println(cozinhaBusca.getId() + " - " + cozinhaBusca.getNome());
		
		
		cozinhaBusca.setNome(cozinhaBusca.getNome() + " - UPDATED");
		cozinhaRepository.salvar(cozinhaBusca);
		
		cozinhas = cozinhaRepository.todas();
		
		for (Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
			
			if (cozinha.getId() <= 4) {
				//cozinhaRepository.remover(cozinha);
			}
		}
		
		cozinhas = cozinhaRepository.todas();
		for (Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
			
			if (cozinha.getId() <= 4) {
				//cozinhaRepository.remover(cozinha);
			}
		}					
	}
	
}
