package com.lampasw.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> todas(){
		return  manager.createQuery("from Estado", Estado.class)
				.getResultList();		
	}
		
	@Transactional
	@Override
	public Estado adicionar(Estado estado) {
		return manager.merge(estado);
	}
		
	@Transactional
	@Override
	public void remover(Estado estado) {
		estado = porId(estado.getId());
		if (estado != null) {
			manager.remove(estado);
		}		
	}
	
	@Override
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}	

}
