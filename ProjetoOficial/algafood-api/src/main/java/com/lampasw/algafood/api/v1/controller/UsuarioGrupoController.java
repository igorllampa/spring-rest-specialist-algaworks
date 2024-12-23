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
import com.lampasw.algafood.api.v1.assembler.GrupoModelAssembler;
import com.lampasw.algafood.api.v1.model.GrupoModel;
import com.lampasw.algafood.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.lampasw.algafood.domain.model.Usuario;
import com.lampasw.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

	@Autowired	
	private CadastroUsuarioService cadastroUsuario;	
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private AlgaLinks algaLinks; 
		
	@Override
	@GetMapping
	public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
	    Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
	    
	    CollectionModel<GrupoModel> gruposModel = grupoModelAssembler.toCollectionModel(usuario.getGrupos())
	            .removeLinks()
	            .add(algaLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));
	    
	    gruposModel.getContent().forEach(grupoModel -> {
	        grupoModel.add(algaLinks.linkToUsuarioGrupoDesassociacao(
	                usuarioId, grupoModel.getId(), "desassociar"));
	    });
	    
	    return gruposModel;
	}   
	
	@Override
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.associarGrupo(usuarioId, grupoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
		
		return ResponseEntity.noContent().build();
	}
}
