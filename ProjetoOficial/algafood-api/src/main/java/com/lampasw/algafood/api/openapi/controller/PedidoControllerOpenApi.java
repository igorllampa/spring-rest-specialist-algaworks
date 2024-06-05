package com.lampasw.algafood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.lampasw.algafood.api.exceptionhandler.Problem;
import com.lampasw.algafood.api.model.PedidoModel;
import com.lampasw.algafood.api.model.PedidoResumoModel;
import com.lampasw.algafood.api.model.input.PedidoInput;
import com.lampasw.algafood.domain.filter.PedidoFilter;
import com.lampasw.algafood.domain.model.Pedido;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiOperation("Pesquisar os pedidos")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes da propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})	
	public Page<PedidoResumoModel> pesquisar (PedidoFilter pedidoFilter, @PageableDefault(size = 10) Pageable pageable);
	
		
	@ApiOperation("Busca pedido por codigo")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes da propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do pedido inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrada", response = Problem.class)
	})
	public PedidoModel buscar(String codigoPedido);
		
	@ApiOperation("Adicionar um pedido")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pedido cadastrado")
	})
	public PedidoModel adicionar(PedidoInput pedidoInput);
			
	@ApiOperation("Atualizar um pedido por Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Pedido atualizado"),
		@ApiResponse(code = 404, message = "Pedido não encontrada", response = Problem.class)
	})
	public Pedido atualizar(Long pedidoId, Pedido pedido);
		
	@ApiOperation("Remover um pedido por Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido excluído"),
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
	})
	public void remover(Long pedidoId);
}
