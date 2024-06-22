package com.lampasw.algafood.api.openapi.controller;

import java.util.List;

import com.lampasw.algafood.api.model.GrupoModel;

import io.swagger.annotations.Api;

@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {
	
	public List<GrupoModel> listar(Long usuarioId);
		
	public void associar(Long usuarioId, Long grupoId);
	
	public void desassociar(Long usuarioId, Long grupoId);
}
