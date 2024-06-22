package com.lampasw.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lampasw.algafood.domain.filter.VendaDiariaFilter;
import com.lampasw.algafood.domain.model.dto.VendaDiaria;

import io.swagger.annotations.Api;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

	
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, 
			String timeOffset);
		
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, 			
			String timeOffset);
}