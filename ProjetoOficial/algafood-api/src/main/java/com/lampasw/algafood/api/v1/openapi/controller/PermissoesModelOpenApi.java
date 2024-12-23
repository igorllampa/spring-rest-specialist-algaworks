package com.lampasw.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.Links;

import com.lampasw.algafood.api.v1.model.PermissaoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("PermissoesModel")
@Data
public class PermissoesModelOpenApi {

    private PermissoesEmbeddedModelOpenApi _embedded;
    private Links _links;
    
    @ApiModel("PermissoesEmbeddedModel")
    @Data
    public class PermissoesEmbeddedModelOpenApi {
        
        private List<PermissaoModel> permissoes;
        
    }   
}