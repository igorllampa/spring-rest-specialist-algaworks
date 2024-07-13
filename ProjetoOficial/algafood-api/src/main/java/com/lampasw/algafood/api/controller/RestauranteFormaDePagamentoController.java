package com.lampasw.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.api.assembler.FormaDePagamentoModelAssembler;
import com.lampasw.algafood.api.model.FormaDePagamentoModel;
import com.lampasw.algafood.api.openapi.controller.RestauranteFormaDePagamentoControllerOpenApi;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-de-pagamento", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaDePagamentoController implements RestauranteFormaDePagamentoControllerOpenApi {
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private FormaDePagamentoModelAssembler formaDePagamentoModelAssembler;
	
	
	@GetMapping
	public CollectionModel<FormaDePagamentoModel> listar(@PathVariable Long restauranteId){
		
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		return formaDePagamentoModelAssembler.toCollectionModel(restaurante.getFormasDePagamento());
	}

	@PutMapping("/{formaDePagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaDePagamentoId) {
		cadastroRestaurante.adicionarFormaDePagamento(restauranteId, formaDePagamentoId);		
	}
	
	@DeleteMapping("/{formaDePagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaDePagamentoId) {
		cadastroRestaurante.desassociarFormaDePagamento(restauranteId, formaDePagamentoId);
	}
}