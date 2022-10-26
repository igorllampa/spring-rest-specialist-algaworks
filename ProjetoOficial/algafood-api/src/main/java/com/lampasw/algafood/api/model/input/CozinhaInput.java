package com.lampasw.algafood.api.model.input;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {
				
	@NotBlank
	@Column(nullable = false)
	private String nome;	
		
	@OneToMany(mappedBy = "cozinha")
	private List<RestauranteInput> restaurante = new ArrayList<>(); 
}