package com.lampasw.algafood.domain.repository;

import com.lampasw.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);

	void delete(FotoProduto foto);
}
