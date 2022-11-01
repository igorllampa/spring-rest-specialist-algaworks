package com.lampasw.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lampasw.algafood.domain.exception.EntidadeEmUsoException;
import com.lampasw.algafood.domain.exception.FormaDePagamentoNaoEncontradaException;
import com.lampasw.algafood.domain.exception.GrupoNaoEncontradoException;
import com.lampasw.algafood.domain.model.Grupo;
import com.lampasw.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	GrupoRepository grupoRepository;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void remover(Long grupoId) {
		try {		
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();
		}catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(grupoId);
        
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(GRUPO_EM_USO, grupoId));
        }	
		
	}
	
	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRepository.findById(grupoId)
				.orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}
}
