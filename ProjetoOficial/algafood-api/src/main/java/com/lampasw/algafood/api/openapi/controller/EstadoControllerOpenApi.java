package com.lampasw.algafood.api.openapi.controller;

import java.util.List;

import com.lampasw.algafood.api.model.EstadoModel;
import com.lampasw.algafood.api.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {
	
	@ApiOperation("Lista os estados")
	public List<EstadoModel> listar();
		
	@ApiOperation("Buscar um estado por Id")
	public EstadoModel buscar(Long estadoId);
		
	@ApiOperation("Adicionar um estado")
	public EstadoModel adicionar(EstadoInput estadoInput);
		
	@ApiOperation("Atualizar um estado por Id")
	public EstadoModel atualizar(Long estadoId, EstadoInput estadoInput);
		
	@ApiOperation("Remover um estado por Id")
	public void remover(Long estadoId);
}
