package com.lampasw.algafood.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lampasw.algafood.domain.exception.NegocioException;
import com.lampasw.algafood.domain.model.Pedido;
import com.lampasw.algafood.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {

	private static final String MSG_STATUS_NAO_PERMITIDO = "Status do pedido %d não pode ser alterado de %s para %s";
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);		
		pedido.confirmar();		
	}
	
	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);		
		pedido.entregar();
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);		
		pedido.cancelar();
	}
}
