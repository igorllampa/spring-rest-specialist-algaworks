package com.lampasw.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lampasw.algafood.domain.model.Restaurante;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

	
	@Query("from Restaurante r join fetch r.cozinha join fetch r.formasDePagamento")
	List<Restaurante> findAll();
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> findByNomeContainingAndCozinhaIdAndTaxaFreteGreaterThanEqual(String nome, Long cozinhaId, BigDecimal taxaFrete);
	
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
		
	List<Restaurante> consultarPorNomeComXmlExterno(String nome, @Param("id") Long cozinhaId);//Esta query está construida no arquivo META-INF/orm.xml
	
	Optional<Restaurante> findFirstByNomeContaining(String nome);
	
	Optional<Restaurante> findTopByNomeContainingOrderByIdDesc(String nome);
	
	List<Restaurante> findTop3ByNomeContainingOrderByNomeDesc(String nome);
	
	List<Restaurante> findComFreteGratis(String nome);		
	
	int countByCozinhaId(Long cozinha);
	
	/*
	List<Restaurante> listar();
	Restaurante buscar (Long id);
	Restaurante salvar (Restaurante restaurante);
	void remover(Restaurante restaurante);
	*/
}
