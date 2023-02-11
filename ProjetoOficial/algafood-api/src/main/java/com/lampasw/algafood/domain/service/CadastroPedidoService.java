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
	public void remover(String codigoPedido) {
					
		try {	
			Pedido pedido = buscarOuFalhar(codigoPedido);
			pedidoRepository.deleteById(pedido.getId());
			pedidoRepository.flush();
		}catch (EmptyResultDataAccessException e) {
            throw new PedidoNaoEncontradoException(codigoPedido);        
        } 		
	}
	
	public Pedido buscarOuFalhar(String codigoPedido) {
		return pedidoRepository.findByCodigo(codigoPedido)
				.orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
	}	
}