package com.lampasw.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.lampasw.algafood.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões")
    CollectionModel<PermissaoModel> listar();
    
}