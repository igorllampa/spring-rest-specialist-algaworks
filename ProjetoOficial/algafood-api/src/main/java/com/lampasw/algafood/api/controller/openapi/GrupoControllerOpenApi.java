package com.lampasw.algafood.api.controller.openapi;

import java.util.List;

import com.lampasw.algafood.api.model.GrupoModel;
import com.lampasw.algafood.api.model.input.GrupoInput;

import io.swagger.annotations.Api;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	public List<GrupoModel> listar();
	
	public GrupoModel buscar(Long grupoId);
	
	public GrupoModel adicionar(GrupoInput grupoInput);
	
	public GrupoModel atualizar(Long grupoId, GrupoInput grupoInput);
	
	public void remover(Long grupoId);	
}
