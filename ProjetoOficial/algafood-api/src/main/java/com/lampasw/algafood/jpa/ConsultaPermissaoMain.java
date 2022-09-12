package com.lampasw.algafood.jpa;

import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.Permissao;
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
		permissaoRepository.save(permissao1);
		
		Permissao permissao2 = new Permissao();
		permissao2.setNome("Acesso aos relatórios");
		permissao2.setDescricao("Permite acesso aos relatórios do sistema");
		permissaoRepository.save(permissao2);
		
		for(Permissao permissao : permissaoRepository.findAll()) {
			System.out.printf("%d - %s - %s \n", permissao.getId(), permissao.getNome(), permissao.getDescricao());
		}
		
		Optional<Permissao> permissaoBusca = permissaoRepository.findById(1L);
		permissaoBusca.get().setNome(permissaoBusca.get().getNome() + " - Updated" );
		Permissao permissaoAtualizada = permissaoRepository.save(permissaoBusca.get());
		
		for(Permissao permissao : permissaoRepository.findAll()) {
			System.out.printf("%d - %s - %s\n", permissao.getId(), permissao.getNome(), permissao.getDescricao());
		}
		
		permissaoRepository.delete(permissaoAtualizada);
		
		for (Permissao permissao : permissaoRepository.findAll()) {
			System.out.printf("%d - %s - %s\n", permissao.getId(), permissao.getNome(), permissao.getDescricao());
		}
	}
	
}
