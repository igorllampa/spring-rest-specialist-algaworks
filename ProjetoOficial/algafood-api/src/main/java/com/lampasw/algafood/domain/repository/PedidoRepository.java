package com.lampasw.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.lampasw.algafood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}