package com.lampasw.algafood.api.v1.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.lampasw.algafood.api.v1.assembler.RestauranteApenasNomeModelAssembler;
import com.lampasw.algafood.api.v1.assembler.RestauranteBasicoModelAssembler;
import com.lampasw.algafood.api.v1.assembler.RestauranteInputDisassembler;
import com.lampasw.algafood.api.v1.assembler.RestauranteModelAssembler;
import com.lampasw.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.lampasw.algafood.api.v1.model.RestauranteBasicoModel;
import com.lampasw.algafood.api.v1.model.RestauranteModel;
import com.lampasw.algafood.api.v1.model.input.RestauranteInput;
import com.lampasw.algafood.api.v1.openapi.controller.RestauranteControllerOpenApi;
import com.lampasw.algafood.core.validation.ValidacaoException;
import com.lampasw.algafood.domain.exception.CidadeNaoEncontradaException;
import com.lampasw.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.exception.NegocioException;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.repository.RestauranteRepository;
import com.lampasw.algafood.domain.service.CadastroRestauranteService;

//@CrossOrigin(methods = {RequestMethod.OPTIONS, RequestMethod.PUT}, origins = "*")
@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;

	@Autowired
	private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;

	private RestauranteRepository restauranteRepository;
	private CadastroRestauranteService cadastroRestaurante;
	private SmartValidator validator;
	private RestauranteModelAssembler restauranteModelAssembler;
	private RestauranteInputDisassembler restauranteInputDisassembler;

	public RestauranteController(RestauranteRepository restauranteRepository,
			CadastroRestauranteService cadastroRestaurante, SmartValidator validator,
			RestauranteModelAssembler restauranteModelAssembler,
			RestauranteInputDisassembler restauranteInputDisassembler) {
		this.restauranteRepository = restauranteRepository;
		this.cadastroRestaurante = cadastroRestaurante;
		this.validator = validator;
		this.restauranteModelAssembler = restauranteModelAssembler;
		this.restauranteInputDisassembler = restauranteInputDisassembler;
	}

	// @CrossOrigin(origins = {"http://www.matafome.local:8001",
	// "http://www.algafood.local:8001", "http://localhost:8001"}) //Habilita apenas
	// para essas três origens
	// @CrossOrigin(origins = "*")//Habilita para todas as origens
	// @CrossOrigin//Sem parametros tb habilita para todas as origens
	@GetMapping
	public CollectionModel<RestauranteBasicoModel> listar() {
		return restauranteBasicoModelAssembler.toCollectionModel(restauranteRepository.findAll());
	}

//	@GetMapping
//	public ResponseEntity<List<RestauranteModel>> listar(){
//		List<RestauranteModel> restaurantesModel = restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
//		
//		return ResponseEntity.ok()
//				//.header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://api.algafood.local:8001") //Insere a permissao especifica para requisicoes vindas dessa origem
//				.header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*") //Libera todas as origens
//				.body(restaurantesModel);
//	}

	// @JsonView(RestauranteView.Resumo.class)
	@GetMapping(params = "projecao=resumo")
	public CollectionModel<RestauranteBasicoModel> listarResumido() {
		return listar();
	}

	// @JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes() {
		return restauranteApenasNomeModelAssembler.toCollectionModel(restauranteRepository.findAll());
	}

//	@GetMapping("/listagem-dinamica")
//	public MappingJacksonValue listarDinamicamente(@RequestParam(required = false) String projecao) {
//		List<Restaurante> restaurantes = restauranteRepository.findAll();
//		List<RestauranteModel> restaurantesModel = restauranteModelAssembler.toCollectionModel(restaurantes);
//
//		MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesModel);
//
//		restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//
//		if ("apenas-nome".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//		} else if ("completo".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(null);
//		}
//
//		return restaurantesWrapper;
//	}

	@GetMapping("/por-taxa-frete")
	public CollectionModel<RestauranteModel> buscarPorTaxa(@RequestParam BigDecimal taxaInicial,
			@RequestParam BigDecimal taxaFinal) {
		return restauranteModelAssembler
				.toCollectionModel(restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal));
	}

	@GetMapping("/por-nome-cozinha-taxa-frete")
	public CollectionModel<RestauranteModel> buscarPorNomeCozinhaTaxa(@RequestParam String nome,
			@RequestParam Long cozinhaId, @RequestParam BigDecimal taxaFrete) {
		return restauranteModelAssembler.toCollectionModel(restauranteRepository
				.findByNomeContainingAndCozinhaIdAndTaxaFreteGreaterThanEqual(nome, cozinhaId, taxaFrete));
	}

	@GetMapping("/total-restaurantes-por-cozinha")
	public int totalRestaurantesPorCozinha(@RequestParam Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}

	@GetMapping("/primero-por-nome")
	public Optional<Restaurante> buscarPrimeiroRestaurantePorNome(@RequestParam String nome) {
		return restauranteRepository.findFirstByNomeContaining(nome);
	}

	@GetMapping("/ultimo-por-nome")
	public Optional<Restaurante> buscarUltimoRestaurantePorNome(@RequestParam String nome) {
		return restauranteRepository.findTopByNomeContainingOrderByIdDesc(nome);
	}

	@GetMapping("/top-tres-restaurantes-por-nome")
	public CollectionModel<RestauranteModel> buscarTopTresRestaurantePorNome(@RequestParam String nome) {
		return restauranteModelAssembler
				.toCollectionModel(restauranteRepository.findTop3ByNomeContainingOrderByNomeDesc(nome));
	}

	@GetMapping("/consultar-por-nome")
	public CollectionModel<RestauranteModel> consultarRestaurantePorNome(@RequestParam String nome,
			@RequestParam Long cozinhaId) {
		return restauranteModelAssembler.toCollectionModel(restauranteRepository.consultarPorNome(nome, cozinhaId));
	}

	@GetMapping("/consultar-por-nome-teste")
	public CollectionModel<RestauranteModel> consultarRestaurantePorNomeConsultaViaXML(@RequestParam String nome,
			@RequestParam Long cozinhaId) {
		return restauranteModelAssembler
				.toCollectionModel(restauranteRepository.consultarPorNomeComXmlExterno(nome, cozinhaId));
	}

	@GetMapping("/consultar-por-nome-por-taxa-frete")
	public CollectionModel<RestauranteModel> consultarRestaurantePorNomeTaxaFreteComMetodoImplementadoEspecifico(
			@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaFreteInicial,
			@RequestParam(required = false) BigDecimal taxaFreteFinal) {
		return restauranteModelAssembler.toCollectionModel(
				restauranteRepository.consultarComJpqlDinamico(nome, taxaFreteInicial, taxaFreteFinal));
	}

	@GetMapping("/consultar-com-criteria")
	public CollectionModel<RestauranteModel> consultarRestauranteComCriteria(
			@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaFreteInicial,
			@RequestParam(required = false) BigDecimal taxaFreteFinal) {
		return restauranteModelAssembler
				.toCollectionModel(restauranteRepository.consultarComCriteria(nome, taxaFreteInicial, taxaFreteFinal));
	}

	@GetMapping("/com-frete-gratis")
	public CollectionModel<RestauranteModel> restaurantesComFreteGratis(String nome) {
		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findComFreteGratis(nome));
	}

	@GetMapping("/primeiro-restaurante")
	public Optional<Restaurante> primeiroRestaurante() {
		return restauranteRepository.buscarPrimeiro();
	}

	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		return restauranteModelAssembler.toModel(restaurante);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

			restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

			// Restaurante restaurante =
			// restauranteInputDisassembler.toDomainObject(restauranteInput);

			// BeanUtils.copyProperties(restaurante, restauranteAtual,
			// "id", "formasDePagamento", "endereco", "dataCadastro");

			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PatchMapping("/{restauranteId}")
	public RestauranteModel atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos,
			HttpServletRequest request) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

		merge(campos, restauranteAtual, request);

		validate(restauranteAtual, "restaurante");

		return atualizar(restauranteId, restauranteModelAssembler.toInputModel(restauranteAtual));
	}

	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.ativar(restauranteIds);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.inativar(restauranteIds);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrir(restauranteId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechar(restauranteId);
		return ResponseEntity.noContent().build();
	}

	private void validate(Restaurante restaurante, String objectName) {

		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, bindingResult);

		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}

	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino, HttpServletRequest request) {

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
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}

}
