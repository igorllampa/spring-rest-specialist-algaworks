package com.lampasw.algafood.domain.repository;

import java.util.List;

import com.lampasw.algafood.domain.model.Cozinha;
import com.lampasw.algafood.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> todas();
	Permissao porId (Long id);
	Permissao adicionar (Permissao permissao);
	void remover(Permissao permissao);
}
