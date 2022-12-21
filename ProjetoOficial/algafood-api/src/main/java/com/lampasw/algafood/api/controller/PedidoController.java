package com.lampasw.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.api.assembler.PedidoModelAssembler;
import com.lampasw.algafood.api.assembler.PedidoResumoModelAssembler;
import com.lampasw.algafood.api.model.PedidoModel;
import com.lampasw.algafood.api.model.PedidoResumoModel;
import com.lampasw.algafood.domain.model.Pedido;
import com.lampasw.algafood.domain.repository.PedidoRepository;
import com.lampasw.algafood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@GetMapping
	public List<PedidoResumoModel> listar () {
		return pedidoResumoModelAssembler.toCollectionModel(pedidoRepository.findAll());
	}
	
	@GetMapping("/{pedidoId}")
	public PedidoModel buscar(@PathVariable Long pedidoId) {
		return pedidoModelAssembler.toModel(emissaoPedido.buscarOuFalhar(pedidoId)); 
	}
	
	@PostMapping
	public Pedido adicionar(@RequestBody @Valid Pedido pedido) {
		return null;
	}
	
	@PutMapping("/{pedidoId}")
	public Pedido atualizar(@PathVariable Long pedidoId, @RequestBody @Valid Pedido pedido) {
		return null;
	}
	
	@DeleteMapping("{pedidoId}")
	public void remover(@PathVariable Long pedidoId) {
		
	}
	
	
}
