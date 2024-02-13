package com.lampasw.algafood.infrastructure.repository;

import org.springframework.stereotype.Repository;

@Repository
public class FormaDePagamentoRepositoryImpl /*implements FormaDePagamentoRepository*/ {

	/*
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<FormaDePagamento> todas(){
		return  manager.createQuery("from FormaDePagamento", FormaDePagamento.class)
				.getResultList();		
	}
		
	@Transactional
	@Override
	public FormaDePagamento adicionar(FormaDePagamento formaDePagamento) {
		return manager.merge(formaDePagamento);
	}
		
	@Transactional
	@Override
	public void remover(FormaDePagamento formaDePagamento) {
		formaDePagamento = porId(formaDePagamento.getId());
		if (formaDePagamento != null) {
			manager.remove(formaDePagamento);
		}		
	}
	
	@Override
	public FormaDePagamento porId(Long id) {
		return manager.find(FormaDePagamento.class, id);
	}	
	*/
}
