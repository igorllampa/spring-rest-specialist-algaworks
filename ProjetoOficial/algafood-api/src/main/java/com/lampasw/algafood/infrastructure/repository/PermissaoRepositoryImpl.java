package com.lampasw.algafood.infrastructure.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PermissaoRepositoryImpl /*implements PermissaoRepository*/ {
	
	/*
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permissao> todas(){
		return  manager.createQuery("from Permissao", Permissao.class)
				.getResultList();		
	}
		
	@Transactional
	@Override
	public Permissao adicionar(Permissao permissao) {
		return manager.merge(permissao);
	}
		
	@Transactional
	@Override
	public void remover(Permissao permissao) {
		permissao = porId(permissao.getId());
		if (permissao != null) {
			manager.remove(permissao);
		}		
	}
	
	@Override
	public Permissao porId(Long id) {
		return manager.find(Permissao.class, id);
	}	
	*/
}
