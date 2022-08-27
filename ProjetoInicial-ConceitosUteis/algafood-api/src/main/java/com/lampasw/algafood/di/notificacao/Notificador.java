package com.lampasw.algafood.di.notificacao;

import com.lampasw.algafood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}