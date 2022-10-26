package com.lampasw.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.lampasw.algafood.domain.exception.EntidadeEmUsoException;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.repository.CozinhaRepository;
import com.lampasw.algafood.domain.service.CadastroCozinhaService;
import com.lampasw.algafood.domain.service.CadastroRestauranteService;
import com.lampasw.algafood.util.DatabaseCleaner;
import com.lampasw.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

	private static final Long COZINHA_ID_INEXISTENTE = 100L;

	private Cozinha cozinhaTailandeza;
	private Cozinha cozinhaAmericana;
	private String jsonCorretoCozinhaChinesa;
	private int quantidadeCozinhasCadastradas;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	
	//@Autowired
	//private Flyway flyway;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/cozinhas";
		RestAssured.port = port;
		
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
				"/json/correto/cozinha-chinesa.json");
		//flyway.migrate();
		
		databaseCleaner.clearTables();
		prepararDados();
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
		assertThrows(EntidadeNaoEncontradaException.class, () -> {
			cadastroCozinha.excluir(COZINHA_ID_INEXISTENTE);
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
	public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {
		
		RestAssured
		.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(quantidadeCozinhasCadastradas))
			.body("nome", Matchers.hasItems("Tailandeza", "Americana"));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured
		.given()
			.body(jsonCorretoCozinhaChinesa)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());		
	}
	
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		RestAssured
		.given()
			.pathParam("cozinhaId", cozinhaAmericana.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaAmericana.getNome()));
	}
	
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		RestAssured
		.given()
			.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());			
	}	
	
	private void prepararDados() {
		cozinhaTailandeza = new Cozinha();
		cozinhaTailandeza.setNome("Tailandeza");
		cozinhaRepository.save(cozinhaTailandeza);
		
		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);
		
		quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
	}
	
}
