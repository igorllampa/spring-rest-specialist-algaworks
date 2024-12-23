package com.lampasw.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.api.v1.AlgaLinks;
import com.lampasw.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.lampasw.algafood.api.v1.model.UsuarioModel;
import com.lampasw.algafood.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private AlgaLinks algaLinks;

	@Override
	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
	    
	    CollectionModel<UsuarioModel> usuariosModel = usuarioModelAssembler
	            .toCollectionModel(restaurante.getResponsaveis())
	                .removeLinks()
	                .add(algaLinks.linkToRestauranteResponsaveis(restauranteId))
	                .add(algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

	    usuariosModel.getContent().stream().forEach(usuarioModel -> {
	        usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(
	                restauranteId, usuarioModel.getId(), "desassociar"));
	    });
	    
	    return usuariosModel;
	}

	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.adicionarResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.removerResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
}
