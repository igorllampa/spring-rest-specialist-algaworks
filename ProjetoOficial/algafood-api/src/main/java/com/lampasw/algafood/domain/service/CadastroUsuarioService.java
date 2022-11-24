package com.lampasw.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lampasw.algafood.domain.exception.EntidadeEmUsoException;
import com.lampasw.algafood.domain.exception.NegocioException;
import com.lampasw.algafood.domain.exception.UsuarioNaoEncontradaException;
import com.lampasw.algafood.domain.model.Usuario;
import com.lampasw.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	private static final String USUARIO_EM_USO = "Usuário não pode ser excluído, pois está em uso.";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	
	@Transactional
	public Usuario salvar(Usuario usuario) {	
		
		usuarioRepository.detach(usuario);//Desacopla a entidade usuario do contexto de persistencia do JPA
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (usuarioExistente.isPresent() && !usuario.equals(usuarioExistente.get())) {
			throw new NegocioException(String.format("Já existe um usuário cadastrado com o email %s.", usuario.getEmail()));
		}
		
		return usuarioRepository.save(usuario);		
	}
	
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		
		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário");
		}
		
		usuario.setSenha(novaSenha);
	}
	
	@Transactional
	public void remover(Long usuarioId) {
		
		try {
			usuarioRepository.deleteById(usuarioId);
			usuarioRepository.flush();
		}catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradaException(usuarioId);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(USUARIO_EM_USO, usuarioId));
		}
	}
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradaException(usuarioId));
	}
}
