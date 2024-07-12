package com.lampasw.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.lampasw.algafood.api.controller.CidadeController;
import com.lampasw.algafood.api.controller.FormaDePagamentoController;
import com.lampasw.algafood.api.controller.PedidoController;
import com.lampasw.algafood.api.controller.RestauranteController;
import com.lampasw.algafood.api.controller.RestauranteProdutoController;
import com.lampasw.algafood.api.controller.UsuarioController;
import com.lampasw.algafood.api.model.PedidoModel;
import com.lampasw.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	 public PedidoModelAssembler() {
	        super(PedidoController.class, PedidoModel.class);
	    }
	    
	    @Override
	    public PedidoModel toModel(Pedido pedido) {
	        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
	        modelMapper.map(pedido, pedidoModel);
	        
	        pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
	        
	        pedidoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
	                .buscar(pedido.getRestaurante().getId())).withSelfRel());
	        
	        pedidoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
	                .buscar(pedido.getCliente().getId())).withSelfRel());
	        
	        // Passamos null no segundo argumento, porque é indiferente para a
	        // construção da URL do recurso de forma de pagamento
	        pedidoModel.getFormaDePagamento().add(linkTo(methodOn(FormaDePagamentoController.class)
	                .buscar(pedido.getFormaDePagamento().getId(), null)).withSelfRel());
	        
	        pedidoModel.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
	                .buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());
	        
	        pedidoModel.getItens().forEach(item -> {
	            item.add(linkTo(methodOn(RestauranteProdutoController.class)
	                    .buscar(pedidoModel.getRestaurante().getId(), item.getProdutoId()))
	                    .withRel("produto"));
	        });
	        
	        return pedidoModel;
	    }
	
	public List<PedidoModel> toCollectionModel(List<Pedido> pedidos){
		return pedidos.stream()
			.map(pedido -> toModel(pedido))
			.collect(Collectors.toList());
	}
}
