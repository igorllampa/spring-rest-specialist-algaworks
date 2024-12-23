package com.lampasw.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.lampasw.algafood.api.exceptionhandler.Problem;
import com.lampasw.algafood.api.v1.model.FormaDePagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaDePagamentoControllerOpenApi {

	@ApiOperation("Lista as formas de pagamento associadas a restaurante")
	@ApiResponses({ @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class) })
	public CollectionModel<FormaDePagamentoModel> listar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);

	@ApiOperation("Associação de restaurante com forma de pagamento")
	@ApiResponses({ @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
			@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class) })
	public ResponseEntity<Void> associar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long formaDePagamentoId);

	@ApiOperation("Desassociação de restaurante com forma de pagamento")
	@ApiResponses({ @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
			@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class) })
	public ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long formaDePagamentoId);
}