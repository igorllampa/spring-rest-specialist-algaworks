package com.lampasw.algafood.jpa;

import java.math.BigDecimal;
import java.util.Optional;

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
				
		Optional<Cozinha> cozinha = cozinhaRepository.findById(1L);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("MC Donalds");
		restaurante1.setTaxaFrete(new BigDecimal(10.15));
		restaurante1.setCozinha(cozinha.get());
		restauranteRepository.save(restaurante1);
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Burger King");
		restaurante2.setTaxaFrete(new BigDecimal(18.95));
		restaurante2.setCozinha(cozinha.get());
		restauranteRepository.save(restaurante2);
		
		for (Restaurante restaurante : restauranteRepository.findAll()) {
			System.out.println(restaurante.getId() + " - " + restaurante.getNome() + " - " + restaurante.getTaxaFrete() + " - " + restaurante.getCozinha().getNome());
		}
					
		Optional<Restaurante> restauranteBusca = restauranteRepository.findById(1L);
		System.out.println(restauranteBusca.get().getId() + " - " + 
						   restauranteBusca.get().getNome() + " - " + 
						   restauranteBusca.get().getTaxaFrete() + " - " + 
						   restauranteBusca.get().getCozinha().getNome());
		
		
		restauranteBusca.get().setNome(restauranteBusca.get().getNome() + " - Monte Verde - MG");
		System.out.println("Atualização:" + restauranteRepository.save(restauranteBusca.get()).toString()); 
		
		for (Restaurante restaurante : restauranteRepository.findAll()) {
			if (restaurante.getId() >= 3)
				restauranteRepository.delete(restaurante);
		}
		
		for (Restaurante restaurante : restauranteRepository.findAll()) {
			System.out.println(restaurante.getId() + " - " + restaurante.getNome() + " - " + restaurante.getTaxaFrete() + " - " + restaurante.getCozinha().getNome());
		}	
	}
	
}
