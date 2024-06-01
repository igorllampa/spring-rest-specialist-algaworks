package com.lampasw.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	@ApiModelProperty(example = "400", position = 5)
	private Integer status;	
	
	@ApiModelProperty(example = "https://algafood.com.br/recurso-nao-encontrado", position = 10)
	private String type;
	
	@ApiModelProperty(example = "Recurso não encontrado", position = 15)
	private String title;
	
	@ApiModelProperty(example = "O recurso '/usuariosx' que você tentou acessar, é inexistente.", position = 20)
	private String detail;	
	
	@ApiModelProperty(example = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.", position = 25)
	private String userMessage;
	
	@ApiModelProperty(example = "2024-06-01T18:21:02.7288382Z", position = 30)
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (Opcional).", position = 35)
	private List<Field> fields;
	
	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Field{
		
		@ApiModelProperty(example = "senha", position = 5)
		private String name;
		
		@ApiModelProperty(example = "Senha do usuário é obrigatória", position = 10)
		private String userMessage;
	}
}
