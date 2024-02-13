package com.lampasw.algafood.infrastructure.repository;

import org.springframework.stereotype.Repository;

@Repository
public class EstadoRepositoryImpl /*implements EstadoRepository*/ {

	/*
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> listar(){
		return  manager.createQuery("from Estado", Estado.class)
				.getResultList();		
	}
		
	@Transactional
	@Override
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}
		
	@Transactional
	@Override
	public void remover(Long id) {
		Estado estado = buscar(id);
		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}			
		manager.remove(estado);
	}
	
	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}
	*/	
}
