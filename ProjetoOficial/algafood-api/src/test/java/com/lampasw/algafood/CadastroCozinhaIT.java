package com.lampasw.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.lampasw.algafood.domain.exception.EntidadeEmUsoException;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.service.CadastroCozinhaService;
import com.lampasw.algafood.domain.service.CadastroRestauranteService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/cozinhas";
		RestAssured.port = port;
	}
	
	@Test
	public void testarCadastroCozinhaComSucesso() {//Test of happy path
		// cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
				
		// ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		// validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
		
	}
	
	@Test
	public void testarCadastroCozinhaSemNome() {
		// cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
			
		
		// ação e validação
		assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinha.salvar(novaCozinha);
		});		
		
		
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		// cenário
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Teste");
		cozinha = cadastroCozinha.salvar(cozinha);
		Long cozinhaId = cozinha.getId();
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("teste");
		restaurante.setTaxaFrete(new BigDecimal(5.00));
		restaurante.setCozinha(cozinha);
		restaurante = cadastroRestaurante.salvar(restaurante);
		
		assertThrows(EntidadeEmUsoException.class, () -> {
			cadastroCozinha.excluir(cozinhaId);
		});
		
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		Long cozinhaId = 999999999999999L;
		
		assertThrows(EntidadeNaoEncontradaException.class, () -> {
			cadastroCozinha.excluir(cozinhaId);
		});
		
		assertFalse(false);
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		
		RestAssured
		.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveConter6Cozinhas_QuandoConsultarCozinhas() {
		
		RestAssured
		.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(6))
			.body("nome", Matchers.hasItems("ITALIANA", "TAILANDEZA"));
	}
	
}
