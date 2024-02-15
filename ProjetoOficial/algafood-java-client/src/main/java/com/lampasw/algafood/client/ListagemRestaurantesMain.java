package com.lampasw.algafood.client;

import org.springframework.web.client.RestTemplate;

import com.lampasw.algafood.client.api.RestauranteClient;

public class ListagemRestaurantesMain {

	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		String urlBase = "http://api.algafood.local:8080";
		
		RestauranteClient restauranteClient = new RestauranteClient(restTemplate, urlBase);
		
		restauranteClient.listar().stream()
			.forEach(restaurante -> System.out.println(restaurante));
	}
}
