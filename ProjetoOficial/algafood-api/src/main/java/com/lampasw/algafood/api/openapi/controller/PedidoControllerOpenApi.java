package com.lampasw.algafood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.lampasw.algafood.api.model.PedidoModel;
import com.lampasw.algafood.api.model.PedidoResumoModel;
import com.lampasw.algafood.api.model.input.PedidoInput;
import com.lampasw.algafood.domain.filter.PedidoFilter;
import com.lampasw.algafood.domain.model.Pedido;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes da propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})	
	public Page<PedidoResumoModel> pesquisar (PedidoFilter pedidoFilter, @PageableDefault(size = 10) Pageable pageable);
	
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes da propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	
	public PedidoModel buscar(String codigoPedido);
		
	public PedidoModel adicionar(PedidoInput pedidoInput);
		
	public Pedido atualizar(Long pedidoId, Pedido pedido);
		
	public void remover(Long pedidoId);

}
