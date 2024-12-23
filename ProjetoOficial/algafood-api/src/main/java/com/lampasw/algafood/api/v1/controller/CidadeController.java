package com.lampasw.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.lampasw.algafood.api.ResourceUriHelper;
import com.lampasw.algafood.api.v1.assembler.CidadeInputDisassembler;
import com.lampasw.algafood.api.v1.assembler.CidadeModelAssembler;
import com.lampasw.algafood.api.v1.model.CidadeModel;
import com.lampasw.algafood.api.v1.model.input.CidadeInput;
import com.lampasw.algafood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.lampasw.algafood.core.web.AlgaMediaTypes;
import com.lampasw.algafood.domain.exception.EstadoNaoEncontradoException;
import com.lampasw.algafood.domain.exception.NegocioException;
import com.lampasw.algafood.domain.model.Cidade;
import com.lampasw.algafood.domain.repository.CidadeRepository;
import com.lampasw.algafood.domain.service.CadastroCidadeService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "cidades", produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@GetMapping(produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
	public CollectionModel<CidadeModel> listar() {
		List<Cidade> cidades = cidadeRepository.findAll();

		return cidadeModelAssembler.toCollectionModel(cidades);									
	}

	@GetMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

		return cidadeModelAssembler.toModel(cidade);		
	}

	@PostMapping(produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			cidade = cadastroCidade.salvar(cidade);

			ResourceUriHelper.addUriInReponseHeader(cidade.getId());

			return cidadeModelAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {

		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

		try {
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

			cidadeAtual = cadastroCidade.salvar(cidadeAtual);

			return cidadeModelAssembler.toModel(cidadeAtual);

		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "ID de uma cidade", example = "0") @PathVariable Long cidadeId) {
		cadastroCidade.remover(cidadeId);
	}

}
