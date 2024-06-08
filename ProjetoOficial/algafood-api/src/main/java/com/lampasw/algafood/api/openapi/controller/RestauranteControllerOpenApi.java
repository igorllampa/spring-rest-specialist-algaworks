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
				
		@ApiOperation("Lista os restaurantes")
		public MappingJacksonValue listarDinamicamente(@RequestParam(required = false) String projecao);
				
		@ApiOperation("Busca restaurantes por taxa")
		public List<RestauranteModel> buscarPorTaxa(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal);
				
		@ApiOperation("Busca restaurantes por nome, cozinha ou taxa")
		public List<RestauranteModel> buscarPorNomeCozinhaTaxa(@RequestParam String nome, @RequestParam Long cozinhaId, @RequestParam BigDecimal taxaFrete);
				
		@ApiOperation("Total de restaurantes por cozinha")
		public int totalRestaurantesPorCozinha(@RequestParam Long cozinhaId);
				
		@ApiOperation("Busca primeiro restaurante por nome")
		public Optional<Restaurante> buscarPrimeiroRestaurantePorNome(@RequestParam String nome);
				
		@ApiOperation("Busca último restaurante por nome")
		public Optional<Restaurante> buscarUltimoRestaurantePorNome(@RequestParam String nome);
				
		@ApiOperation("Busca top três restaurantes por nome")
		public List<RestauranteModel> buscarTopTresRestaurantePorNome(@RequestParam String nome);
				
		@ApiOperation("Busca restaurantes por nome e cozinha")
		public List<RestauranteModel> consultarRestaurantePorNome(@RequestParam String nome, @RequestParam Long cozinhaId);
				
		@ApiOperation("Consulta restaurante por nome via xml")
		public List<RestauranteModel> consultarRestaurantePorNomeConsultaViaXML(@RequestParam String nome, @RequestParam Long cozinhaId);
		
		@ApiOperation("Buaca restaurante por nome, taxa frete")
		public List<RestauranteModel> consultarRestaurantePorNomeTaxaFreteComMetodoImplementadoEspecifico(
				@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaFreteInicial, @RequestParam(required = false) BigDecimal taxaFreteFinal);
				
		@ApiOperation("Consulta restaurantes com criteria")
		public List<RestauranteModel> consultarRestauranteComCriteria(@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaFreteInicial, @RequestParam(required = false) BigDecimal taxaFreteFinal);
				
		@ApiOperation("Lista os resturante com frete grátis por nome")
		public List<RestauranteModel> restaurantesComFreteGratis(String nome);
				
		@ApiOperation("Primeiro restaurante")
		public Optional<Restaurante> primeiroRestaurante();
				
		@ApiOperation("Busca restaurante por Id")
		public RestauranteModel buscar(@PathVariable Long restauranteId);
			
		@ApiOperation("Adiciona restaurante")
		public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput);
				
		@ApiOperation("Atualiza restaurante")
		public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput);	
				
		@ApiOperation("Atualiza restaurante parcialmente")
		public RestauranteModel atualizarParcial(@PathVariable Long restauranteId, 
				@RequestBody Map<String, Object> campos, HttpServletRequest request);
		
		@ApiOperation("Ativa restaurante por Id")
		public void ativar(@PathVariable Long restauranteId);
				
		@ApiOperation("Inativa restaurante por Id")
		public void inativar(@PathVariable Long restauranteId);
						
		@ApiOperation("Ativar multiplos restaurantes por Ids")
		public void ativarMultiplos(@RequestBody List<Long> restauranteIds);
				
		@ApiOperation("Desativa multiplos restaurantes por Ids")
		public void desativarMultiplos(@RequestBody List<Long> restauranteIds);
				
		@ApiOperation("Abrir restaurante")
		public void abrir(@PathVariable Long restauranteId);
		
		@ApiOperation("Fechar restaurante")
		public void fechar(@PathVariable Long restauranteId);
}
