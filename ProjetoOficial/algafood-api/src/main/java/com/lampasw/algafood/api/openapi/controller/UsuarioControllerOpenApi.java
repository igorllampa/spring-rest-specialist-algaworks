package com.lampasw.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.lampasw.algafood.api.exceptionhandler.Problem;
import com.lampasw.algafood.api.model.UsuarioModel;
import com.lampasw.algafood.api.model.input.SenhaInput;
import com.lampasw.algafood.api.model.input.UsuarioComSenhaInput;
import com.lampasw.algafood.api.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {
	
	@ApiOperation("Lista os usuários")
	public CollectionModel<UsuarioModel> listar();
		
	@ApiOperation("Busca um usuário por ID")	
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public UsuarioModel buscar(
			@ApiParam(value = "Id de um usuário", example = "1", required = true) Long usuarioId);
	
	@ApiOperation("Cadastra um usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado")
	})
	public UsuarioModel adicionar(
			@ApiParam(name="corpo", value = "Representação de um novo usuário", required = true)
			UsuarioComSenhaInput usuarioInput);
		
	@ApiOperation("Atualiza um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuário atualizado"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public UsuarioModel atualizar(
			@ApiParam(value="Id de um usuário", example = "1", required = true) 
			Long usuarioId,
			@ApiParam(name="corpo", value = "Representação de um usuário com os novos dados", required = true)
			UsuarioInput usuarioInput);
		
	@ApiOperation("Atualiza senha de um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Senha do usuário atualizada"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public void atualizarSenha(
			@ApiParam(value="Id de um usuário", example = "1", required = true) 
			Long usuarioId, 
			@ApiParam(name = "corpo", value = "Representação de uma nova senha", required = true) 
			SenhaInput senhaInput);
	
	@ApiOperation("Exclui um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Usuário excluído"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public void remover(
			@ApiParam(value="Id de um usuário", example = "1", required = true) 
			Long usuarioId);
}
