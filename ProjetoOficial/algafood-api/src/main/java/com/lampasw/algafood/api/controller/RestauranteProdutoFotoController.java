package com.lampasw.algafood.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lampasw.algafood.api.assembler.FotoProdutoModelAssembler;
import com.lampasw.algafood.api.model.FotoProdutoModel;
import com.lampasw.algafood.api.model.input.FotoProdutoInput;
import com.lampasw.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.lampasw.algafood.domain.model.FotoProduto;
import com.lampasw.algafood.domain.model.Produto;
import com.lampasw.algafood.domain.repository.ProdutoRepository;
import com.lampasw.algafood.domain.service.CadastroProdutoService;
import com.lampasw.algafood.domain.service.CatalogoFotoProdutoService;
import com.lampasw.algafood.domain.service.FotoStorageService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
		
		return fotoProdutoModelAssembler.toModel(fotoProduto);
	}
	
	@GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @RequestHeader(name = "accept") String accepHeader) 
					throws HttpMediaTypeNotAcceptableException{
		try {
			
		
		FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
		
		MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
		List<MediaType> mediaTypeAceitas = MediaType.parseMediaTypes(accepHeader);
		
		verificarCompatibilidadeMediaType(mediaTypeAceitas, mediaTypeFoto);
		
		InputStream arquivo = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
		
		return ResponseEntity.ok()
				.contentType(mediaTypeFoto)
				.body(new InputStreamResource(arquivo));
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}		
		
	}
	
	private void verificarCompatibilidadeMediaType(List<MediaType> mediaTypeAceitas, MediaType mediaTypeFoto) 
			throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypeAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypeAceitas);
		}
	}

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
		
		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
		
		MultipartFile arquivo = fotoProdutoInput.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		foto.setTamanho(arquivo.getSize());
		
		
		FotoProduto fotoSalva = catalogoFotoProduto.salvarFoto(foto, arquivo.getInputStream());
		
		return fotoProdutoModelAssembler.toModel(fotoSalva);
		
//		var nomeArquivo = UUID.randomUUID().toString() + 
//				"_" + fotoProdutoInput.getArquivo().getOriginalFilename();
//		
//		var arquivoFoto = Path.of("C:\\temp\\algaworks-foto-produto", nomeArquivo);
//		
//		
//		try {
//			fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
//		} catch (IOException e) {		
//			e.printStackTrace();
//		}
		
		
	}
}