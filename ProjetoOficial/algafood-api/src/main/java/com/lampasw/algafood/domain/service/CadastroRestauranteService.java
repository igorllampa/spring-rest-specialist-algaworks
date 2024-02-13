package com.lampasw.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lampasw.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.lampasw.algafood.domain.model.Cidade;
import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.FormaDePagamento;
import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.model.Usuario;
import com.lampasw.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {	

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;	

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CadastroFormaDePagamentoService cadastroFormaDePagamento;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
				
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);
		
		return restauranteRepository.save(restaurante);								
	}
	
	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);		
		restauranteAtual.ativar();		
	}

	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);		
		restauranteAtual.inativar();		
	}
	
	@Transactional
	public void ativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this :: ativar);
	}
	
	@Transactional
	public void inativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this :: inativar);
	}
	
	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		restauranteAtual.abrir();
	}
	
	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		restauranteAtual.fechar();
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(() -> 
			new RestauranteNaoEncontradoException(restauranteId));
	}
	
	@Transactional
	public void desassociarFormaDePagamento(Long restauranteId, Long formaDePagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaDePagamento formaDePagamento = cadastroFormaDePagamento.buscarOuFalhar(formaDePagamentoId);		
		
		restaurante.removerFormaDePagamento(formaDePagamento);		
	}
	
	@Transactional 
	public void adicionarFormaDePagamento(Long restauranteId, Long formaDePagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaDePagamento formaDePagamento = cadastroFormaDePagamento.buscarOuFalhar(formaDePagamentoId);
		
		restaurante.adicionarFormaDePagamento(formaDePagamento);
	}
	
	@Transactional
	public void adicionarResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId); 
		
		restaurante.adicionarResponsavel(usuario);
	}
	
	@Transactional
	public void removerResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		
		restaurante.removerResponsavel(usuario);
	}
}
