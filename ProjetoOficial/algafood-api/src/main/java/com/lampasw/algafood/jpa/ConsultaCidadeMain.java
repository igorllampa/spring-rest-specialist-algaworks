package com.lampasw.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.Cidade;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.CidadeRepository;
import com.lampasw.algafood.domain.repository.CozinhaRepository;
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
		estadoRepository.adicionar(estado);
				
		Estado estadoBahia = estadoRepository.porId(3L);
		Cidade cidade1 = new Cidade();
		cidade1.setNome("Porto Seguro");
		cidade1.setEstado(estadoBahia);		
		cidadeRepository.adicionar(cidade1);
							
		for (Cidade cidade : cidadeRepository.todas()) {
			System.out.println(cidade.getNome() + " - " + cidade.getEstado().getNome());
		}
				
		Cidade cidadeBusca = cidadeRepository.porId(2L);
		cidadeBusca.setNome(cidadeBusca.getNome() + " - Updated");
		cidadeRepository.adicionar(cidadeBusca);
		
		for (Cidade cidade : cidadeRepository.todas()) {
			System.out.printf("%s - %s \n", cidade.getNome(), cidade.getEstado().getNome());			
		}					
				
		for (Cidade cidade : cidadeRepository.todas()) {
			if(cidade.getId() >= 2) {
				cidadeRepository.remover(cidade);
			}
		}
		
		for(Cidade cidade : cidadeRepository.todas()) {
			System.out.printf("%d - %s - %s \n", cidade.getId(), cidade.getNome(), cidade.getEstado().getNome());
		}				
	}	
}
