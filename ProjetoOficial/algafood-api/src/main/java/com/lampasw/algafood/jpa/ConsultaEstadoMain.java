package com.lampasw.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.CozinhaRepository;
import com.lampasw.algafood.domain.repository.EstadoRepository;

public class ConsultaEstadoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		Estado estado1 = new Estado();
		estado1.setNome("Espírito Santo");
		estadoRepository.salvar(estado1);
		
		Estado estado2 = new Estado();
		estado2.setNome("Rio Grande do Sul");
		estadoRepository.salvar(estado2);
		
		Estado estado3 = new Estado();
		estado3.setNome("Santa Catarina");
		estadoRepository.salvar(estado3);
						
		for(Estado estado : estadoRepository.listar()) {
			System.out.printf("%d - %s \n", estado.getId(), estado.getNome());
		}
		
		Estado estadoBusca = estadoRepository.buscar(4L);
		estadoBusca.setNome("Rio G. do Sul - Updated");
		estadoRepository.salvar(estadoBusca);
		
		for(Estado estado : estadoRepository.listar()) {
			System.out.printf("%d - %s \n", estado.getId(), estado.getNome());
		}
		
		//estadoRepository.remover(estadoRepository.buscar(4L));
		
		for(Estado estado : estadoRepository.listar()) {
			System.out.printf("%d - %s \n", estado.getId(), estado.getNome());					
		}			
	}
	
}
