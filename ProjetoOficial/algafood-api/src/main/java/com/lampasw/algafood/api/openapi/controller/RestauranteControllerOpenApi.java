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
import io.swagger.annotations.ApiParam;

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
		public List<RestauranteModel> buscarPorTaxa(
				@ApiParam(value = "Valor da taxa inicial", example = "1.00") @RequestParam BigDecimal taxaInicial, 
				@ApiParam(value = "Valor da taxa final", example = "100.50") @RequestParam BigDecimal taxaFinal);
				
		@ApiOperation("Busca restaurantes por nome, cozinha ou taxa")
		public List<RestauranteModel> buscarPorNomeCozinhaTaxa(
				@ApiParam(value = "Nome da cozinha", example = "Brasileira") @RequestParam String nome, 
				@ApiParam(value = "Id da cozinha", example = "0") @RequestParam Long cozinhaId, 
				@ApiParam(value = "Valor da taxa de frete", example = "7.80") @RequestParam BigDecimal taxaFrete);
				
		@ApiOperation("Total de restaurantes por cozinha")
		public int totalRestaurantesPorCozinha(@ApiParam(value = "ID de uma cozinha", example = "0") @RequestParam Long cozinhaId);
				
		@ApiOperation("Busca primeiro restaurante por nome")
		public Optional<Restaurante> buscarPrimeiroRestaurantePorNome(@ApiParam(value = "Nome de uma cozinha", example = "Francesa") @RequestParam String nome);
				
		@ApiOperation("Busca último restaurante por nome")
		public Optional<Restaurante> buscarUltimoRestaurantePorNome(@ApiParam(value = "Nome de uma cozinha", example = "Brasileira") @RequestParam String nome);
				
		@ApiOperation("Busca top três restaurantes por nome")
		public List<RestauranteModel> buscarTopTresRestaurantePorNome(@ApiParam(value = "Nome de uma cozinha", example = "Italiana") @RequestParam String nome);
				
		@ApiOperation("Busca restaurantes por nome e cozinha")
		public List<RestauranteModel> consultarRestaurantePorNome(
				@ApiParam(value = "Nome de uma cozinha", example = "Brasileira") @RequestParam String nome, 
				@ApiParam(value = "Id de uma cozinha", example = "0") @RequestParam Long cozinhaId);
				
		@ApiOperation("Consulta restaurante por nome via xml")
		public List<RestauranteModel> consultarRestaurantePorNomeConsultaViaXML(
				@ApiParam(value = "Nome de uma cozinha", example = "Brasileira") @RequestParam String nome, 
				@ApiParam(value = "Id de uma cozinha", example = "0") @RequestParam Long cozinhaId);
		
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
		public RestauranteModel buscar(@ApiParam(value = "Id de um restaurante", example = "0") @PathVariable Long restauranteId);
			
		@ApiOperation("Adiciona restaurante")
		public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput);
				
		@ApiOperation("Atualiza restaurante")
		public RestauranteModel atualizar(
				@ApiParam(value = "Id de um restaurante", example = "0") @PathVariable Long restauranteId, 
				@RequestBody @Valid RestauranteInput restauranteInput);	
				
		@ApiOperation("Atualiza restaurante parcialmente")
		public RestauranteModel atualizarParcial(
				@ApiParam(value = "Id de um restaurante", example = "0") @PathVariable Long restauranteId, 
				@RequestBody Map<String, Object> campos, 
				HttpServletRequest request);
		
		@ApiOperation("Ativa restaurante por Id")
		public void ativar(@ApiParam(value = "Id de um restaurante", example = "0") @PathVariable Long restauranteId);
				
		@ApiOperation("Inativa restaurante por Id")
		public void inativar(@ApiParam(value = "Id de um restaurante", example = "0") @PathVariable Long restauranteId);
						
		@ApiOperation("Ativar multiplos restaurantes por Ids")
		public void ativarMultiplos(@RequestBody List<Long> restauranteIds);
				
		@ApiOperation("Desativa multiplos restaurantes por Ids")
		public void desativarMultiplos(@RequestBody List<Long> restauranteIds);
				
		@ApiOperation("Abrir restaurante")
		public void abrir(@ApiParam(value = "Id de um restaurante", example = "0") @PathVariable Long restauranteId);
		
		@ApiOperation("Fechar restaurante")
		public void fechar(@ApiParam(value = "Id de um restaurante", example = "0") @PathVariable Long restauranteId);
}
