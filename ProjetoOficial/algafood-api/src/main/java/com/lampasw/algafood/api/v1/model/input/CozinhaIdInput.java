package com.lampasw.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInput {

	@ApiModelProperty(example = "0", required = true)
	@NotNull
	private Long id;
}
