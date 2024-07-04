package com.lampasw.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lampasw.algafood.api.assembler.UsuarioInputDisassembler;
import com.lampasw.algafood.api.assembler.UsuarioModelAssembler;
import com.lampasw.algafood.api.model.UsuarioModel;
import com.lampasw.algafood.api.model.input.SenhaInput;
import com.lampasw.algafood.api.model.input.UsuarioComSenhaInput;
import com.lampasw.algafood.api.model.input.UsuarioInput;
import com.lampasw.algafood.api.openapi.controller.UsuarioControllerOpenApi;
import com.lampasw.algafood.domain.model.Usuario;
import com.lampasw.algafood.domain.repository.UsuarioRepository;
import com.lampasw.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;	
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@GetMapping
	public CollectionModel<UsuarioModel> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();		
		return usuarioModelAssembler.toCollectionModel(usuarios);
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		usuario = cadastroUsuario.salvar(usuario);		
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
		
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		
		usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
		
		return usuarioModelAssembler.toModel(usuarioAtual);
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput ) {
		cadastroUsuario.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void remover(@PathVariable Long usuarioId) {
		cadastroUsuario.remover(usuarioId);
	}
}
