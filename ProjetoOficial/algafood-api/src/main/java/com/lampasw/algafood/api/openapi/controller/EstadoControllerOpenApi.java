package com.lampasw.algafood.api.openapi.controller;

import java.util.List;

import com.lampasw.algafood.api.exceptionhandler.Problem;
import com.lampasw.algafood.api.model.EstadoModel;
import com.lampasw.algafood.api.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {
	
	@ApiOperation("Lista os estados")
	public List<EstadoModel> listar();
		
	@ApiOperation("Buscar um estado por Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado é inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	public EstadoModel buscar(Long estadoId);
		
	@ApiOperation("Adicionar um estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado cadastrado")
	})
	public EstadoModel adicionar(EstadoInput estadoInput);
		
	@ApiOperation("Atualizar um estado por Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado atualizado"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	public EstadoModel atualizar(Long estadoId, EstadoInput estadoInput);
		
	@ApiOperation("Remover um estado por Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado excluído"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	public void remover(Long estadoId);
}
