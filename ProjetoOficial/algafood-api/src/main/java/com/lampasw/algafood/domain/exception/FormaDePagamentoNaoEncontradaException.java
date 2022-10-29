package com.lampasw.algafood.domain.exception;

public class FormaDePagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public FormaDePagamentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public FormaDePagamentoNaoEncontradaException(Long formaDePagamentoId) {
		this(String.format("A forma de pagamento %d não foi encontrada.", formaDePagamentoId));
	}
}
