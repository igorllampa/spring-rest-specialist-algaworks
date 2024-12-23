package com.lampasw.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.lampasw.algafood.api.assembler.PedidoInputDisassembler;
import com.lampasw.algafood.api.assembler.PedidoModelAssembler;
import com.lampasw.algafood.api.assembler.PedidoResumoModelAssembler;
import com.lampasw.algafood.api.model.PedidoModel;
import com.lampasw.algafood.api.model.PedidoResumoModel;
import com.lampasw.algafood.api.model.input.PedidoInput;
import com.lampasw.algafood.api.openapi.controller.PedidoControllerOpenApi;
import com.lampasw.algafood.core.data.PageWrapper;
import com.lampasw.algafood.core.data.PageableTranslator;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.exception.NegocioException;
import com.lampasw.algafood.domain.filter.PedidoFilter;
import com.lampasw.algafood.domain.model.Pedido;
import com.lampasw.algafood.domain.model.Usuario;
import com.lampasw.algafood.domain.repository.PedidoRepository;
import com.lampasw.algafood.domain.service.EmissaoPedidoService;
import com.lampasw.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

//	@GetMapping
//	public MappingJacksonValue listar () {//Exemplo de como retornar apenas campos especificos da representacao do recurso
//		List<Pedido> pedidos = pedidoRepository.findAll();
//		List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidos);
//		
//		MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);
//		
//		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept("codigo", "valorTotal"));
//		pedidosWrapper.setFilters(filterProvider);
//		
//		return pedidosWrapper;
//	}

	@GetMapping
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter pedidoFilter,
			@PageableDefault(size = 10) Pageable pageable) {

		Pageable pageableTraduzido = traduzirPageable(pageable);

		Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(pedidoFilter), pageableTraduzido);

		pedidosPage = new PageWrapper<>(pedidosPage, pageable);

		return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
	}

	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		return pedidoModelAssembler.toModel(emissaoPedido.buscarOuFalhar(codigoPedido));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{pedidoId}")
	public Pedido atualizar(@PathVariable Long pedidoId, @RequestBody @Valid Pedido pedido) {
		return null;
	}

	@DeleteMapping("{pedidoId}")
	public void remover(@PathVariable Long pedidoId) {

	}

	private Pageable traduzirPageable(Pageable apiPageable) {

		var mapeamento = ImmutableMap.of("codigo", "codigo", "restaurante.nome", "restaurante.nome", "nomeCliente",
				"cliente.nome", "valorTotal", "valorTotal");

		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
