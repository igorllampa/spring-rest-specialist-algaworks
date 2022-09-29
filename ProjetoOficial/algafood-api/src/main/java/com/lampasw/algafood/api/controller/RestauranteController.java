package com.lampasw.algafood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lampasw.algafood.core.validation.Groups;
import com.lampasw.algafood.core.validation.ValidacaoException;
import com.lampasw.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.exception.NegocioException;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.repository.RestauranteRepository;
import com.lampasw.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {
	
	private RestauranteRepository restauranteRepository;
	private CadastroRestauranteService cadastroRestaurante;
	private SmartValidator validator;
	
	public RestauranteController(RestauranteRepository restauranteRepository,
								CadastroRestauranteService cadastroRestaurante,
								SmartValidator validator) {		
		this.restauranteRepository = restauranteRepository;
		this.cadastroRestaurante = cadastroRestaurante;
		this.validator = validator;
	}
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/por-taxa-frete")
	public List<Restaurante> buscarPorTaxa(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/por-nome-cozinha-taxa-frete")
	public List<Restaurante> buscarPorNomeCozinhaTaxa(@RequestParam String nome, @RequestParam Long cozinhaId, @RequestParam BigDecimal taxaFrete){
		return restauranteRepository.findByNomeContainingAndCozinhaIdAndTaxaFreteGreaterThanEqual(nome, cozinhaId, taxaFrete);
	}
	
	@GetMapping("/total-restaurantes-por-cozinha")
	public int totalRestaurantesPorCozinha(@RequestParam Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	
	@GetMapping("/primero-por-nome")
	public Optional<Restaurante> buscarPrimeiroRestaurantePorNome(@RequestParam String nome){
		return restauranteRepository.findFirstByNomeContaining(nome);
	}
	
	@GetMapping("/ultimo-por-nome")
	public Optional<Restaurante> buscarUltimoRestaurantePorNome(@RequestParam String nome){
		return restauranteRepository.findTopByNomeContainingOrderByIdDesc(nome);
	}
	
	@GetMapping("/top-tres-restaurantes-por-nome")
	public List<Restaurante> buscarTopTresRestaurantePorNome(@RequestParam String nome){
		return restauranteRepository.findTop3ByNomeContainingOrderByNomeDesc(nome);
	}
	
	@GetMapping("/consultar-por-nome")
	public List<Restaurante> consultarRestaurantePorNome(@RequestParam String nome, @RequestParam Long cozinhaId){
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/consultar-por-nome-teste")
	public List<Restaurante> consultarRestaurantePorNomeConsultaViaXML(@RequestParam String nome, @RequestParam Long cozinhaId){
		return restauranteRepository.consultarPorNomeComXmlExterno(nome, cozinhaId);
	}
	
	@GetMapping("/consultar-por-nome-por-taxa-frete")
	public List<Restaurante> consultarRestaurantePorNomeTaxaFreteComMetodoImplementadoEspecifico(
			@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaFreteInicial, @RequestParam(required = false) BigDecimal taxaFreteFinal){
		return restauranteRepository.consultarComJpqlDinamico(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/consultar-com-criteria")
	public List<Restaurante> consultarRestauranteComCriteria(@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaFreteInicial, @RequestParam(required = false) BigDecimal taxaFreteFinal){
		return restauranteRepository.consultarComCriteria(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome){			
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/primeiro-restaurante")
	public Optional<Restaurante> primeiroRestaurante(){
		return restauranteRepository.buscarPrimeiro();
	}
	
	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId) {		
		return cadastroRestaurante.buscarOuFalhar(restauranteId);	
	}
		
	@PostMapping	
	@ResponseStatus(code = HttpStatus.CREATED)
	public Restaurante salvar(@RequestBody @Valid Restaurante restaurante) {
		try {
			return cadastroRestaurante.salvar(restaurante);
		}catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody @Valid Restaurante restaurante ){
		try {		
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);			 
			
			BeanUtils.copyProperties(restaurante, restauranteAtual, 
					"id", "formasDePagamento", "endereco", "dataCadastro");	
					
			return cadastroRestaurante.salvar(restauranteAtual);
		}catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}	
	
	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable Long restauranteId, 
			@RequestBody Map<String, Object> campos, HttpServletRequest request){
		
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
							
		merge(campos, restauranteAtual, request);
		
		validate(restauranteAtual, "restaurante");
		
		return atualizar(restauranteId, restauranteAtual);
	}

	private void validate(Restaurante restaurante, String objectName) {
		
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
		
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino,
			HttpServletRequest request) {
		
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);		
			
			camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);
				
				
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				ReflectionUtils.setField(field, restauranteDestino, novoValor);					
			});
		}catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}
}
