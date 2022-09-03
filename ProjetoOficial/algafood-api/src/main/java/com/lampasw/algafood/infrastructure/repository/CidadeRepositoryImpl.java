package com.lampasw.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.lampasw.algafood.domain.model.Cidade;
import com.lampasw.algafood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> todas(){
		return  manager.createQuery("from Cidade", Cidade.class)
				.getResultList();		
	}
		
	@Transactional
	@Override
	public Cidade adicionar(Cidade cidade) {
		return manager.merge(cidade);
	}
		
	@Transactional
	@Override
	public void remover(Cidade cidade) {
		cidade = porId(cidade.getId());
		if (cidade != null) {
			manager.remove(cidade);
		}		
	}
	
	@Override
	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}	

}
