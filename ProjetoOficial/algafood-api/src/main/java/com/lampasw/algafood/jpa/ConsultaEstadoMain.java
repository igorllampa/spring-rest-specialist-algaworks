package com.lampasw.algafood.jpa;

import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.EstadoRepository;

public class ConsultaEstadoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		Estado estado1 = new Estado();
		estado1.setNome("Espírito Santo");
		estadoRepository.save(estado1);
		
		Estado estado2 = new Estado();
		estado2.setNome("Rio Grande do Sul");
		estadoRepository.save(estado2);
		
		Estado estado3 = new Estado();
		estado3.setNome("Santa Catarina");
		estadoRepository.save(estado3);
						
		for(Estado estado : estadoRepository.findAll()) {
			System.out.printf("%d - %s \n", estado.getId(), estado.getNome());
		}
		
		Optional<Estado> estadoBusca = estadoRepository.findById(4L);
		estadoBusca.get().setNome("Rio G. do Sul - Updated");
		estadoRepository.save(estadoBusca.get());
		
		for(Estado estado : estadoRepository.findAll()) {
			System.out.printf("%d - %s \n", estado.getId(), estado.getNome());
		}
		
		//estadoRepository.remover(estadoRepository.buscar(4L));
		
		for(Estado estado : estadoRepository.findAll()) {
			System.out.printf("%d - %s \n", estado.getId(), estado.getNome());					
		}			
	}
	
}
