package com.lampasw.algafood.api.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lampasw.algafood.api.exceptionhandler.Problem;
import com.lampasw.algafood.api.model.FotoProdutoModel;
import com.lampasw.algafood.api.model.input.FotoProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {
	
	@ApiOperation(value = "Busca a foto do produto de um restaurante",
            produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
    })
	public FotoProdutoModel buscar(
		@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
		@ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

		
	@ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
	public ResponseEntity<?> servirFoto(
			Long restauranteId, 
			Long produtoId, 
			@RequestHeader(name = "accept") String accepHeader)throws HttpMediaTypeNotAcceptableException;

	@ApiOperation("Atualiza a foto do produto de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Foto do produto atualizada"),
        @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
    })
	public FotoProdutoModel atualizarFoto(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			 @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId, 
			FotoProdutoInput fotoProdutoInput)throws IOException;
		
	 @ApiOperation("Exclui a foto do produto de um restaurante")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Foto do produto excluída"),
	        @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
	        @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
	    })
	public void excluir(
			 @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			 @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);
}
