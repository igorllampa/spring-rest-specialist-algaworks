package com.lampasw.algafood.api.openapi.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.lampasw.algafood.api.model.RestauranteApenasNomeModel;
import com.lampasw.algafood.api.model.RestauranteBasicoModel;
import com.lampasw.algafood.api.model.RestauranteModel;
import com.lampasw.algafood.api.model.input.RestauranteInput;
import com.lampasw.algafood.api.openapi.model.RestauranteBasicoModelOpenApi;
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
			@ApiImplicitParam(value = "Nome da projeção de pedidos", name = "projecao", paramType = "query", type = "string", allowableValues = "apenas-nome, resumo") })
	public CollectionModel<RestauranteBasicoModel> listar();

	@ApiOperation(value = "Lista restaurantes", hidden = true, response = RestauranteBasicoModelOpenApi.class)
	public CollectionModel<RestauranteBasicoModel> listarResumido();

	@ApiOperation(value = "Lista restaurantes", hidden = true)
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

//	@ApiOperation("Lista os restaurantes")
//	public MappingJacksonValue listarDinamicamente(String projecao);

	@ApiOperation("Busca restaurantes por taxa")
	public CollectionModel<RestauranteModel> buscarPorTaxa(
			@ApiParam(value = "Valor da taxa inicial", example = "1.00") BigDecimal taxaInicial,
			@ApiParam(value = "Valor da taxa final", example = "100.50") BigDecimal taxaFinal);

	@ApiOperation("Busca restaurantes por nome, cozinha ou taxa")
	public CollectionModel<RestauranteModel> buscarPorNomeCozinhaTaxa(
			@ApiParam(value = "Nome da cozinha", example = "Brasileira") String nome,
			@ApiParam(value = "Id da cozinha", example = "0") Long cozinhaId,
			@ApiParam(value = "Valor da taxa de frete", example = "7.80") BigDecimal taxaFrete);

	@ApiOperation("Total de restaurantes por cozinha")
	public int totalRestaurantesPorCozinha(@ApiParam(value = "ID de uma cozinha", example = "0") Long cozinhaId);

	@ApiOperation("Busca primeiro restaurante por nome")
	public Optional<Restaurante> buscarPrimeiroRestaurantePorNome(
			@ApiParam(value = "Nome de uma cozinha", example = "Francesa") String nome);

	@ApiOperation("Busca último restaurante por nome")
	public Optional<Restaurante> buscarUltimoRestaurantePorNome(
			@ApiParam(value = "Nome de uma cozinha", example = "Brasileira") String nome);

	@ApiOperation("Busca top três restaurantes por nome")
	public CollectionModel<RestauranteModel> buscarTopTresRestaurantePorNome(
			@ApiParam(value = "Nome de uma cozinha", example = "Italiana") String nome);

	@ApiOperation("Busca restaurantes por nome e cozinha")
	public CollectionModel<RestauranteModel> consultarRestaurantePorNome(
			@ApiParam(value = "Nome de uma cozinha", example = "Brasileira") String nome,
			@ApiParam(value = "Id de uma cozinha", example = "0") Long cozinhaId);

	@ApiOperation("Consulta restaurante por nome via xml")
	public CollectionModel<RestauranteModel> consultarRestaurantePorNomeConsultaViaXML(
			@ApiParam(value = "Nome de uma cozinha", example = "Brasileira") String nome,
			@ApiParam(value = "Id de uma cozinha", example = "0") Long cozinhaId);

	@ApiOperation("Buaca restaurante por nome, taxa frete")
	public CollectionModel<RestauranteModel> consultarRestaurantePorNomeTaxaFreteComMetodoImplementadoEspecifico(
			String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	@ApiOperation("Consulta restaurantes com criteria")
	public CollectionModel<RestauranteModel> consultarRestauranteComCriteria(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal);

	@ApiOperation("Lista os resturante com frete grátis por nome")
	public CollectionModel<RestauranteModel> restaurantesComFreteGratis(String nome);

	@ApiOperation("Primeiro restaurante")
	public Optional<Restaurante> primeiroRestaurante();

	@ApiOperation("Busca restaurante por Id")
	public RestauranteModel buscar(
			@ApiParam(value = "Id de um restaurante", example = "0", required = true) Long restauranteId);

	@ApiOperation("Adiciona restaurante")
	public RestauranteModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true) RestauranteInput restauranteInput);

	@ApiOperation("Atualiza restaurante")
	public RestauranteModel atualizar(
			@ApiParam(value = "Id de um restaurante", example = "0", required = true) Long restauranteId,
			@ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados", required = true) RestauranteInput restauranteInput);

	@ApiOperation("Atualiza restaurante parcialmente")
	public RestauranteModel atualizarParcial(
			@ApiParam(value = "Id de um restaurante", example = "0", required = true) Long restauranteId,
			Map<String, Object> campos, HttpServletRequest request);

	@ApiOperation("Ativa restaurante por Id")
	public ResponseEntity<Void> ativar(
			@ApiParam(value = "Id de um restaurante", example = "0", required = true) Long restauranteId);

	@ApiOperation("Inativa restaurante por Id")
	public ResponseEntity<Void> inativar(
			@ApiParam(value = "Id de um restaurante", example = "0", required = true) Long restauranteId);

	@ApiOperation("Ativar multiplos restaurantes por Ids")
	public void ativarMultiplos(List<Long> restauranteIds);

	@ApiOperation("Desativa multiplos restaurantes por Ids")
	public void desativarMultiplos(List<Long> restauranteIds);

	@ApiOperation("Abrir restaurante")
	public ResponseEntity<Void> abrir(
			@ApiParam(value = "Id de um restaurante", example = "0", required = true) Long restauranteId);

	@ApiOperation("Fechar restaurante")
	public ResponseEntity<Void> fechar(
			@ApiParam(value = "Id de um restaurante", example = "0", required = true) Long restauranteId);
}
