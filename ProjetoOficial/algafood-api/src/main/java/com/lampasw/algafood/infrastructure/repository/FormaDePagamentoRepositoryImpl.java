package com.lampasw.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.lampasw.algafood.domain.model.FormaDePagamento;
import com.lampasw.algafood.domain.repository.FormaDePagamentoRepository;

@Component
public class FormaDePagamentoRepositoryImpl implements FormaDePagamentoRepository {
	
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

}
