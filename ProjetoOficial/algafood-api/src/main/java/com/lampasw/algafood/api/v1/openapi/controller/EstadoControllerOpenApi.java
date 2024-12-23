package com.lampasw.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.lampasw.algafood.api.exceptionhandler.Problem;
import com.lampasw.algafood.api.v1.model.EstadoModel;
import com.lampasw.algafood.api.v1.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {
	
	@ApiOperation("Lista os estados")
	public CollectionModel<EstadoModel> listar();
		
	@ApiOperation("Buscar um estado por Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado é inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	public EstadoModel buscar(@ApiParam(value = "ID de um estado", example = "0", required = true) Long estadoId);
		
	@ApiOperation("Adicionar um estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado cadastrado")
	})
	public EstadoModel adicionar(
			@ApiParam(name="corpo", value = "Representação de um novo estado", required = true) EstadoInput estadoInput);
		
	@ApiOperation("Atualizar um estado por Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado atualizado"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	public EstadoModel atualizar(
			@ApiParam(value = "ID de um estado", example = "0", required = true) Long estadoId, 
			@ApiParam(name="corpo", value = "Representação de um estado com os novos dados", required = true) EstadoInput estadoInput);
		
	@ApiOperation("Remover um estado por Id") 
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado excluído"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	public void remover(@ApiParam(value = "ID de um estado", example = "0", required = true) Long estadoId);
}
