package com.lampasw.algafood.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.lampasw.algafood.client.api.ClientApiException;
import com.lampasw.algafood.client.api.RestauranteClient;
import com.lampasw.algafood.client.model.RestauranteModel;
import com.lampasw.algafood.client.model.input.CidadeInput;
import com.lampasw.algafood.client.model.input.CozinhaInput;
import com.lampasw.algafood.client.model.input.EnderecoInput;
import com.lampasw.algafood.client.model.input.RestauranteInput;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			String urlBase = "http://api.algafood.local:8080";
			
			RestauranteClient restauranteClient = new RestauranteClient(restTemplate, urlBase);
			
			RestauranteInput restauranteInput = fillRestauranteInput();
			
			RestauranteModel restaurante = restauranteClient.incluir(restauranteInput);
			
			System.out.println(restaurante);
		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
				
				e.getProblem().getObjects().stream()
					.forEach(p -> System.out.println("- " + p.getUserMessage()));
				
			} else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}
	}

	private static RestauranteInput fillRestauranteInput() {
		CozinhaInput cozinhaInput = new CozinhaInput();
		cozinhaInput.setId(1L);
		
		CidadeInput cidadeInput = new CidadeInput();
		cidadeInput.setId(1L);
		
		EnderecoInput enderecoInput = new EnderecoInput();
		enderecoInput.setBairro("Louvre");
		enderecoInput.setCep("123789");
		enderecoInput.setCidade(cidadeInput);
		enderecoInput.setComplemento("Parri Boulevard");
		enderecoInput.setLogradouro("Angers francoas");
		enderecoInput.setNumero("109a");
		
		RestauranteInput restauranteInput = new RestauranteInput();
		restauranteInput.setNome("");
		restauranteInput.setTaxaFrete(new BigDecimal(18));
		restauranteInput.setCozinha(cozinhaInput);
		restauranteInput.setEndereco(enderecoInput);
		return restauranteInput;
	}
}
