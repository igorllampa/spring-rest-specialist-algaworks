package com.lampasw.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.lampasw.algafood.domain.model.Estado;
import com.lampasw.algafood.domain.repository.EstadoRepository;

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
