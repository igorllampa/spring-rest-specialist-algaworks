package com.lampasw.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;

import com.lampasw.algafood.api.exceptionhandler.Problem;
import com.lampasw.algafood.api.model.CozinhaModel;
import com.lampasw.algafood.api.model.CozinhasXmlWrapper;
import com.lampasw.algafood.api.model.input.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@ApiOperation("Lista as cozinhas")
	public PagedModel<CozinhaModel> listar(Pageable pageable);

	@ApiOperation("Lista cozinhas em formato xml")
	public CozinhasXmlWrapper listarXml();

	@ApiOperation("Busca cozinha(s) por Nome")
	@ApiResponses({ @ApiResponse(code = 400, message = "Nome da cozinha inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class) })
	public CollectionModel<CozinhaModel> buscarPorNome(
			@ApiParam(value = "Nome de uma cozinha", example = "Italiana") String nome);

	@ApiOperation("Checa se o nome da cozinha existe")
	@ApiResponses({ @ApiResponse(code = 400, message = "Nome da cozinha inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class) })
	public boolean checarExisteCozinha(@ApiParam(value = "Nome de uma cozinha", example = "Italiana") String nome);

	@ApiResponses({ @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class) })
	@ApiOperation("Busca uma cozinha por ID")
	public CozinhaModel buscar(@ApiParam(value = "Id de uma cozinha", example = "0", required = true) Long cozinhaId);

	@ApiResponses({ @ApiResponse(code = 201, message = "Cozinha cadastrada") })
	@ApiOperation("Adiciona uma nova cozinha")
	public CozinhaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true) CozinhaInput cozinhaInput);

	@ApiResponses({ @ApiResponse(code = 200, message = "Cozinha atualizada"),
			@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class) })
	@ApiOperation("Atualiza uma cozinha por ID com os novos dados")
	public CozinhaModel atualizar(
			@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados") CozinhaInput cozinhaInput,
			@ApiParam(value = "Id de uma cozinha", example = "0", required = true) Long cozinhaId);

	@ApiResponses({ @ApiResponse(code = 204, message = "Cozinha excluída"),
			@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class) })
	@ApiOperation("Exclui uma cozinha por ID")
	public void remover(@ApiParam(value = "Id de uma cozinha", example = "0", required = true) Long cozinhaId);

}
