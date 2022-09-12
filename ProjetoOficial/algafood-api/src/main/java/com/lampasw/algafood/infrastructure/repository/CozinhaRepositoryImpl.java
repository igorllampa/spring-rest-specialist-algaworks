package com.lampasw.algafood.infrastructure.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CozinhaRepositoryImpl /*implements CozinhaRepository*/ {

	/*
	//This methods were developed without Spring Data Jpa, using directly JPQL at the implementation
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> todas(){
		return  manager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();		
	}
		
	@Override
	public List<Cozinha> consultarPorNome(String nome) {
		return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
				.setParameter("nome", "%"+nome+"%")
				.getResultList();
	}
	
	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
		
	@Transactional
	@Override
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);
		
		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}	
		
		manager.remove(cozinha);
	}
	
	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}	
	*/
}
