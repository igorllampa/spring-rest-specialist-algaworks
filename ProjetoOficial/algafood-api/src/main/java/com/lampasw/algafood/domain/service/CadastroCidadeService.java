package com.lampasw.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lampasw.algafood.domain.exception.EntidadeEmUsoException;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.model.Cidade;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.CidadeRepository;
import com.lampasw.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		
		//Verify if state exists		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscar(estadoId); 
		if (estado == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("O estado de código %d não existe.", estadoId));			
		}
		
		cidade.setEstado(estado);
		return cidadeRepository.salvar(cidade);
	}
	
	public void remover(Long cidadeId) {
		try {			
			cidadeRepository.remover(cidadeId);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("A cidade de código %d está em uso.", cidadeId));
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("A cidade de código %d não existe", cidadeId));
		}			
	}
}
