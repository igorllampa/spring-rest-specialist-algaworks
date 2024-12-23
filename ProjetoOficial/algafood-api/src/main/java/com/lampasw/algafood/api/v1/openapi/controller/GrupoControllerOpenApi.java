package com.lampasw.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.lampasw.algafood.api.exceptionhandler.Problem;
import com.lampasw.algafood.api.v1.model.GrupoModel;
import com.lampasw.algafood.api.v1.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista os grupos")
	public CollectionModel<GrupoModel> listar();

	@ApiOperation("Busca um grupo por Id")
	@ApiResponses({ @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Grupo não encontrada", response = Problem.class) })
	public GrupoModel buscar(@ApiParam(value = "ID de um grupo", example = "0", required = true) Long grupoId);

	@ApiOperation("Adiciona um novo grupo")
	@ApiResponses({ @ApiResponse(code = 201, message = "Grupo cadastrado") })
	public GrupoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true) GrupoInput grupoInput);

	@ApiOperation("Atualiza um grupo por Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Grupo atualizado"),
			@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class) })
	public GrupoModel atualizar(@ApiParam(value = "ID de um grupo", example = "0", required = true) Long grupoId,
			@ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", required = true) GrupoInput grupoInput);

	@ApiOperation("Remove um grupo por Id")
	@ApiResponses({ @ApiResponse(code = 204, message = "Grupo excluído"),
			@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class) })
	public void remover(@ApiParam(value = "ID de um grupo", example = "0", required = true) Long grupoId);
}
