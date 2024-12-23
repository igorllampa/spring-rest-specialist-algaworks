package com.lampasw.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.Links;

import com.lampasw.algafood.api.v1.model.FormaDePagamentoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("FormasPagamentoModel")
@Data
public class FormasDePagamentoModelOpenApi {

    private FormasDePagamentoEmbeddedModelOpenApi _embedded;
    private Links _links;
    
    @ApiModel("FormasDePagamentoEmbeddedModel")
    @Data
    public class FormasDePagamentoEmbeddedModelOpenApi {
        
        private List<FormaDePagamentoModel> formasPagamento;
        
    }   
}