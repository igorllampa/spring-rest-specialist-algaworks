package com.lampasw.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.api.assembler.ProdutoInputDisassembler;
import com.lampasw.algafood.api.assembler.ProdutoModelAssembler;
import com.lampasw.algafood.api.model.ProdutoModel;
import com.lampasw.algafood.domain.model.Produto;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.repository.ProdutoRepository;
import com.lampasw.algafood.domain.service.CadastroProdutoService;
import com.lampasw.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos/")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired 
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
		
	@GetMapping
	public List<ProdutoModel> listar(@PathVariable Long restauranteId){
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		List<Produto> produtos = produtoRepository.findByRestaurante(restaurante);
		
		return produtoModelAssembler.toCollectionModel(produtos);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId){
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		
		return produtoModelAssembler.toModel(produto);
	}
	
	
}
