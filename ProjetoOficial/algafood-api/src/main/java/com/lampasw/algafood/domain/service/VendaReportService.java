package com.lampasw.algafood.domain.service;

import com.lampasw.algafood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	
	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
