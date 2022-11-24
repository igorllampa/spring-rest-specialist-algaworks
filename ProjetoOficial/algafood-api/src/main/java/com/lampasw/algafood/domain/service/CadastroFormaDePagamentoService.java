package com.lampasw.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lampasw.algafood.domain.exception.EntidadeEmUsoException;
import com.lampasw.algafood.domain.exception.FormaDePagamentoNaoEncontradaException;
import com.lampasw.algafood.domain.model.FormaDePagamento;
import com.lampasw.algafood.domain.repository.FormaDePagamentoRepository;

@Service
public class CadastroFormaDePagamentoService {

	private static final String MSG_FORMA_PAGAMENTO_EM_USO 
    	= "Forma de pagamento de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private FormaDePagamentoRepository formaDePagamentoRepository;
	
	public FormaDePagamento buscarOuFalhar(Long formaDePagamentoId) {
		return formaDePagamentoRepository.findById(formaDePagamentoId)
				.orElseThrow(() -> new FormaDePagamentoNaoEncontradaException(formaDePagamentoId) );
	}
	
	@Transactional
	public FormaDePagamento salvar(FormaDePagamento formaDePagamento) {
		return formaDePagamentoRepository.save(formaDePagamento);
	}
	
	public void remover(Long formaDePagamentoId) {		
		
		try {
			formaDePagamentoRepository.deleteById(formaDePagamentoId);
			formaDePagamentoRepository.flush();
            
        } catch (EmptyResultDataAccessException e) {
            throw new FormaDePagamentoNaoEncontradaException(formaDePagamentoId);
        
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_FORMA_PAGAMENTO_EM_USO, formaDePagamentoId));
        }	
	}
	
}
