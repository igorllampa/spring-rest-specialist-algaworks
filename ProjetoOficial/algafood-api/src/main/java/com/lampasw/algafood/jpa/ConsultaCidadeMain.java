package com.lampasw.algafood.jpa;

import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.Cidade;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.CidadeRepository;
import com.lampasw.algafood.domain.repository.EstadoRepository;

public class ConsultaCidadeMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
				
		Estado estado = new Estado();		
		estado.setNome("Bahia");
		estadoRepository.save(estado);
				
		Optional<Estado> estadoBahia = estadoRepository.findById(3L);
		Cidade cidade1 = new Cidade();
		cidade1.setNome("Porto Seguro");
		cidade1.setEstado(estadoBahia.get());		
		cidadeRepository.save(cidade1);
							
		for (Cidade cidade : cidadeRepository.findAll()) {
			System.out.println(cidade.getNome() + " - " + cidade.getEstado().getNome());
		}
				
		Optional<Cidade> cidadeBusca = cidadeRepository.findById(2L);
		cidadeBusca.get().setNome(cidadeBusca.get().getNome() + " - Updated");
		cidadeRepository.save(cidadeBusca.get());
		
		for (Cidade cidade : cidadeRepository.findAll()) {
			System.out.printf("%s - %s \n", cidade.getNome(), cidade.getEstado().getNome());			
		}					
				
		for (Cidade cidade : cidadeRepository.findAll()) {
			if(cidade.getId() >= 2) {
				//cidadeRepository.remover(cidade);
			}
		}
		
		for(Cidade cidade : cidadeRepository.findAll()) {
			System.out.printf("%d - %s - %s \n", cidade.getId(), cidade.getNome(), cidade.getEstado().getNome());
		}				
	}	
}
