package com.lampasw.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lampasw.algafood.domain.exception.EntidadeEmUsoException;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}
	
	public void remover(Long estadoId) {
		try {
			estadoRepository.remover(estadoId);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("O estado não foi encontrado");
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("O estado está em uso");
		}
	}
}