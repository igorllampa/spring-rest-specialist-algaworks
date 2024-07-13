package com.lampasw.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.AlgaLinks;
import com.lampasw.algafood.api.controller.FormaDePagamentoController;
import com.lampasw.algafood.api.model.FormaDePagamentoModel;
import com.lampasw.algafood.domain.model.FormaDePagamento;

@Component
public class FormaDePagamentoModelAssembler 
        extends RepresentationModelAssemblerSupport<FormaDePagamento, FormaDePagamentoModel> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    public FormaDePagamentoModelAssembler() {
        super(FormaDePagamentoController.class, FormaDePagamentoModel.class);
    }
    
    @Override
    public FormaDePagamentoModel toModel(FormaDePagamento FormaDePagamento) {
        FormaDePagamentoModel FormaDePagamentoModel = 
                createModelWithId(FormaDePagamento.getId(), FormaDePagamento);
        
        modelMapper.map(FormaDePagamento, FormaDePagamentoModel);
        
        FormaDePagamentoModel.add(algaLinks.linkToFormasDePagamento("formasPagamento"));
        
        return FormaDePagamentoModel;
    }
    
    @Override
    public CollectionModel<FormaDePagamentoModel> toCollectionModel(Iterable<? extends FormaDePagamento> entities) {
        return super.toCollectionModel(entities)
            .add(algaLinks.linkToFormasDePagamento());
    }   
}