package com.lampasw.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.AlgaLinks;
import com.lampasw.algafood.api.controller.PedidoController;
import com.lampasw.algafood.api.model.PedidoResumoModel;
import com.lampasw.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }
    
	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
	    PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
	    modelMapper.map(pedido, pedidoModel);
	    	    
	    pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
	    
	    pedidoModel.getRestaurante().add(
	            algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

	    pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
	    
	    return pedidoModel;
	}
	
	public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos){
		return pedidos.stream()
			.map(pedido -> toModel(pedido))
			.collect(Collectors.toList());
	}
}
