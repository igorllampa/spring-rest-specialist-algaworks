package com.lampasw.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lampasw.algafood.domain.exception.PedidoNaoEncontradoException;
import com.lampasw.algafood.domain.model.Pedido;
import com.lampasw.algafood.domain.repository.PedidoRepository;

@Service
public class CadastroPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido); 
	}
	
	@Transactional
	public void remover(Long pedidoId) {
					
		try {		
			pedidoRepository.deleteById(pedidoId);
			pedidoRepository.flush();
		}catch (EmptyResultDataAccessException e) {
            throw new PedidoNaoEncontradoException(pedidoId);        
        } 		
	}
	
	public Pedido buscarOuFalhar(Long pedidoId) {
		return pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}	
}