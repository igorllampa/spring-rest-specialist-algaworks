package com.lampasw.algafood.domain.service;

import java.util.List;

import com.lampasw.algafood.domain.filter.VendaDiariaFilter;
import com.lampasw.algafood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	 
}