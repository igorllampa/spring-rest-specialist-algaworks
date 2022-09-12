package com.lampasw.algafood.jpa;

import java.util.List;
import java.util.Optional;

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
		cozinhaRepository.save(cozinha1);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Portuguesa");
		cozinhaRepository.save(cozinha2);
					
		List<Cozinha> cozinhas = cozinhaRepository.findAll();
		
		for (Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
		}		
		
		Optional<Cozinha> cozinhaBusca = cozinhaRepository.findById(1L);
		if (cozinhaBusca.isPresent()) {
			System.out.println(cozinhaBusca.get().getId() + " - " + cozinhaBusca.get().getNome());
			cozinhaBusca.get().setNome(cozinhaBusca.get().getNome() + " - UPDATED");
			cozinhaRepository.save(cozinhaBusca.get());
		}		
					
		cozinhas = cozinhaRepository.findAll();
		
		for (Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
			
			if (cozinha.getId() <= 4) {
				//cozinhaRepository.remover(cozinha);
			}
		}
		
		cozinhas = cozinhaRepository.findAll();
		for (Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
			
			if (cozinha.getId() <= 4) {
				//cozinhaRepository.remover(cozinha);
			}
		}					
	}
	
}
