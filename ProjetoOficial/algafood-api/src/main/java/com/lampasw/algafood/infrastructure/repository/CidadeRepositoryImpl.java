package com.lampasw.algafood.infrastructure.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CidadeRepositoryImpl /*implements CidadeRepository*/{
//	
//	@PersistenceContext
//	private EntityManager manager;
//	
//	@Override
//	public List<Cidade> listar(){
//		return  manager.createQuery("from Cidade", Cidade.class)
//				.getResultList();		
//	}
//		
//	@Transactional
//	@Override
//	public Cidade salvar(Cidade cidade) {
//		return manager.merge(cidade);
//	}
//		
//	@Transactional
//	@Override
//	public void remover(Long id) {
//		Cidade cidade = buscar(id);
//		if (cidade == null) {
//			throw new EmptyResultDataAccessException(1);
//		}		
//		manager.remove(cidade);
//	}
//	
//	@Override
//	public Cidade buscar(Long id) {
//		return manager.find(Cidade.class, id);
//	}	

}
