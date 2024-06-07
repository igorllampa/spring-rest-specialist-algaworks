package com.lampasw.algafood.api.openapi.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lampasw.algafood.api.model.RestauranteModel;
import com.lampasw.algafood.api.model.input.RestauranteInput;
import com.lampasw.algafood.domain.model.Restaurante;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

		@ApiOperation(value = "Lista restaurantes")
		@ApiImplicitParams({
			@ApiImplicitParam(value = "Nome da projeção de pedidos", 
					name = "projecao", paramType = "query", type = "string", allowableValues = "apenas-nome, resumo")
		})
		public List<RestauranteModel> listar();		
		
		@ApiOperation(value = "Lista restaurantes", hidden = true)	
		public List<RestauranteModel> listarResumido();
		
		@ApiOperation(value = "Lista restaurantes", hidden = true)
		public List<RestauranteModel> listarApenasNomes();
				
		public MappingJacksonValue listarDinamicamente(@RequestParam(required = false) String projecao);
				
		public List<RestauranteModel> buscarPorTaxa(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal);
				
		public List<RestauranteModel> buscarPorNomeCozinhaTaxa(@RequestParam String nome, @RequestParam Long cozinhaId, @RequestParam BigDecimal taxaFrete);
				
		public int totalRestaurantesPorCozinha(@RequestParam Long cozinhaId);
				
		public Optional<Restaurante> buscarPrimeiroRestaurantePorNome(@RequestParam String nome);
				
		public Optional<Restaurante> buscarUltimoRestaurantePorNome(@RequestParam String nome);
				
		public List<RestauranteModel> buscarTopTresRestaurantePorNome(@RequestParam String nome);
				
		public List<RestauranteModel> consultarRestaurantePorNome(@RequestParam String nome, @RequestParam Long cozinhaId);
				
		public List<RestauranteModel> consultarRestaurantePorNomeConsultaViaXML(@RequestParam String nome, @RequestParam Long cozinhaId);
		
		public List<RestauranteModel> consultarRestaurantePorNomeTaxaFreteComMetodoImplementadoEspecifico(
				@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaFreteInicial, @RequestParam(required = false) BigDecimal taxaFreteFinal);
				
		public List<RestauranteModel> consultarRestauranteComCriteria(@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaFreteInicial, @RequestParam(required = false) BigDecimal taxaFreteFinal);
				
		public List<RestauranteModel> restaurantesComFreteGratis(String nome);
				
		public Optional<Restaurante> primeiroRestaurante();
				
		public RestauranteModel buscar(@PathVariable Long restauranteId);
			
		public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput);
				
		public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput);	
				
		public RestauranteModel atualizarParcial(@PathVariable Long restauranteId, 
				@RequestBody Map<String, Object> campos, HttpServletRequest request);
		
		public void ativar(@PathVariable Long restauranteId);
				
		public void inativar(@PathVariable Long restauranteId);
						
		public void ativarMultiplos(@RequestBody List<Long> restauranteIds);
				
		public void desativarMultiplos(@RequestBody List<Long> restauranteIds);
				
		public void abrir(@PathVariable Long restauranteId);
		
		public void fechar(@PathVariable Long restauranteId);
}
