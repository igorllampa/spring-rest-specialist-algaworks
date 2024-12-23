package com.lampasw.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.lampasw.algafood.api.exceptionhandler.Problem;
import com.lampasw.algafood.api.v1.model.FormaDePagamentoModel;
import com.lampasw.algafood.api.v1.model.input.FormaDePagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas de Pagamento")
public interface FormaDePagamentoControllerOpenApi {
	
	@ApiOperation(value = "Lista as formas de pagamento", response = FormasDePagamentoModelOpenApi.class)
	public ResponseEntity<CollectionModel<FormaDePagamentoModel>> listar(ServletWebRequest request);
		
	@ApiOperation("Busca uma forma de pagamento por ID")	
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	public ResponseEntity<FormaDePagamentoModel> buscar(			
			@ApiParam(value = "ID de uma forma de pagamento", example = "0", required = true) Long formaDePagamentoId,
			ServletWebRequest request);
		
	@ApiOperation("Cadastra uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Forma de pagamento cadastrada")
	})
	public FormaDePagamentoModel adicionar(@ApiParam(name="corpo", value = "Representação de uma nova forma de pagamento", required = true) FormaDePagamentoInput formaDePagamentoInput);
		
	@ApiOperation("Atualiza uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	public FormaDePagamentoModel atualizar(@ApiParam(value = "ID de uma forma de pagamento", example = "0", required = true)  Long formaDePagamentoId, 
			@ApiParam(name="corpo", value = "Representação de uma forma de pagamento com os novos dados", required = true) FormaDePagamentoInput formaDePagamentoInput);
		
	@ApiOperation("Exclui uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de pagamento excluída"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	public void remover(@ApiParam(value = "ID de uma forma de pagamento", example = "0", required = true) Long formaDePagamentoId);	
}
