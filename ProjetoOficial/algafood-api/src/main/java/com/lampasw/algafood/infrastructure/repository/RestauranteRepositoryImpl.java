package com.lampasw.algafood.infrastructure.repository;

import static com.lampasw.algafood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.lampasw.algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.lampasw.algafood.domain.model.Restaurante;
import com.lampasw.algafood.domain.repository.RestauranteRepository;
import com.lampasw.algafood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl /*implements RestauranteRepository*/ implements RestauranteRepositoryQueries {
	
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;
	
	//Consulta dinamica com JPQL
	@Override
	public List<Restaurante> consultarComJpqlDinamico(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		
		var jpql = new StringBuilder();
		var parameter = new HashMap<String, Object>();
		
		jpql.append("  from Restaurante");
		jpql.append(" where 1 = 1");
		
		if (StringUtils.hasLength(nome)) {
			jpql.append(" and nome like :nome");
			parameter.put("nome", "%"+nome+"%");
		}
		
		if (taxaFreteInicial != null) {
			jpql.append(" and taxaFrete >= :taxaInicial");
			parameter.put("taxaInicial", taxaFreteInicial);
		}
		
		if (taxaFreteFinal != null) {
			jpql.append(" and taxaFrete <= :taxaFinal");
			parameter.put("taxaFinal", taxaFreteFinal);
		}
		
		var query =  manager.createQuery(jpql.toString(), Restaurante.class);
		parameter.forEach((chave, valor) -> query.setParameter(chave, valor));
				
		
		return query.getResultList();
	}


	@Override
	public List<Restaurante> consultarComCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasLength(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%" ));			
		}
		if (taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}
		if (taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
						
		Predicate[] predicados = new Predicate[predicates.size()];
        predicados = predicates.toArray(predicados);
        
		criteria.where(predicados);
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		
		return query.getResultList();		
	}
	
	public List<Restaurante> findComFreteGratis(String nome){
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
	
	//Query com Criteria
	
	
	/*
	@Override
	public List<Restaurante> listar(){
		return  manager.createQuery("from Restaurante", Restaurante.class)
				.getResultList();		
	}
		
	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
		
	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
		restaurante = buscar(restaurante.getId());
		if (restaurante != null) {
			manager.remove(restaurante);
		}		
	}
	
	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}
	*/	
}
