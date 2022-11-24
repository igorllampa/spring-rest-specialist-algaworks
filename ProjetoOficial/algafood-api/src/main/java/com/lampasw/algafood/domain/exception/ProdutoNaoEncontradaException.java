package com.lampasw.algafood.domain.exception;

public class ProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public ProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
		this(String.format("Não existe um produto de código %d, no restaurante de código %d.", produtoId, restauranteId));
	}
}
