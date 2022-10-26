package com.lampasw.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lampasw.algafood.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@JsonRootName("gastronomia")
public class Cozinha {
		
	@NotNull(groups = Groups.CozinhaId.class)
	@EqualsAndHashCode.Include
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	//@JsonProperty("cozinhaId")
	private Long id;
			
	//@JsonIgnore //Apenas não exibe este campo no retorno da Api
	//@JsonProperty("cozinhaTitulo")
	@NotBlank
	@Column(nullable = false)
	private String nome;	
		
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurante = new ArrayList<>(); 
}
