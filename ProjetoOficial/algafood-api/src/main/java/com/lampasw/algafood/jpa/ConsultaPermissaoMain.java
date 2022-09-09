package com.lampasw.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Permissao;
import com.lampasw.algafood.domain.repository.CozinhaRepository;
import com.lampasw.algafood.domain.repository.PermissaoRepository;

public class ConsultaPermissaoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);
		
		Permissao permissao1 = new Permissao();
		permissao1.setNome("Acesso Financeiro");
		permissao1.setDescricao("Permite acesso às funcionalidades financeira do sistema");
		permissaoRepository.adicionar(permissao1);
		
		Permissao permissao2 = new Permissao();
		permissao2.setNome("Acesso aos relatórios");
		permissao2.setDescricao("Permite acesso aos relatórios do sistema");
		permissaoRepository.adicionar(permissao2);
		
		for(Permissao permissao : permissaoRepository.todas()) {
			System.out.printf("%d - %s - %s \n", permissao.getId(), permissao.getNome(), permissao.getDescricao());
		}
		
		Permissao permissaoBusca = permissaoRepository.porId(1L);
		permissaoBusca.setNome(permissaoBusca.getNome() + " - Updated" );
		permissaoBusca = permissaoRepository.adicionar(permissaoBusca);
		
		for(Permissao permissao : permissaoRepository.todas()) {
			System.out.printf("%d - %s - %s\n", permissao.getId(), permissao.getNome(), permissao.getDescricao());
		}
		
		permissaoRepository.remover(permissaoBusca);
		
		for (Permissao permissao : permissaoRepository.todas()) {
			System.out.printf("%d - %s - %s\n", permissao.getId(), permissao.getNome(), permissao.getDescricao());
		}
	}
	
}
