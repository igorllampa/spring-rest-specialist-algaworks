package com.lampasw.algafood.jpa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.lampasw.algafood.AlgafoodApiApplication;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.repository.CozinhaRepository;
import com.lampasw.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
				
		Cozinha cozinha = cozinhaRepository.buscar(1L);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("MC Donalds");
		restaurante1.setTaxaFrete(new BigDecimal(10.15));
		restaurante1.setCozinha(cozinha);
		restauranteRepository.adicionar(restaurante1);
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Burger King");
		restaurante2.setTaxaFrete(new BigDecimal(18.95));
		restaurante2.setCozinha(cozinha);
		restauranteRepository.adicionar(restaurante2);
		
		for (Restaurante restaurante : restauranteRepository.todos()) {
			System.out.println(restaurante.getId() + " - " + restaurante.getNome() + " - " + restaurante.getTaxaFrete() + " - " + restaurante.getCozinha().getNome());
		}
					
		Restaurante restauranteBusca = restauranteRepository.porId(1L);
		System.out.println(restauranteBusca.getId() + " - " + restauranteBusca.getNome() + " - " + restauranteBusca.getTaxaFrete() + " - " + restauranteBusca.getCozinha().getNome());
		
		
		restauranteBusca.setNome(restauranteBusca.getNome() + " - Monte Verde - MG");
		System.out.println("Atualização:" + restauranteRepository.adicionar(restauranteBusca).toString()); 
		
		for (Restaurante restaurante : restauranteRepository.todos()) {
			if (restaurante.getId() >= 3)
				restauranteRepository.remover(restaurante);
		}
		
		for (Restaurante restaurante : restauranteRepository.todos()) {
			System.out.println(restaurante.getId() + " - " + restaurante.getNome() + " - " + restaurante.getTaxaFrete() + " - " + restaurante.getCozinha().getNome());
		}
		
	}
	
}
