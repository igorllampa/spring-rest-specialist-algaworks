package com.lampasw.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.lampasw.algafood.core.validation.Groups;
import com.lampasw.algafood.core.validation.Multiplo;
import com.lampasw.algafood.core.validation.TaxaFrete;
import com.lampasw.algafood.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	private String nome;
		
	@Column(name = "taxa_frete", nullable = false)
	//@PositiveOrZero
	@TaxaFrete
	@Multiplo(numero = 5)
	@NotNull
	private BigDecimal taxaFrete;
			
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@NotNull	
	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne()
	@JoinColumn(name = "cozinha_id", nullable = false)	
	private Cozinha cozinha;
		
	@Embedded
	private Endereco endereco;
		
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="restaurante_forma_de_pagamento",
			   joinColumns = @JoinColumn(name = "restaurante_id"),
			   inverseJoinColumns = @JoinColumn(name = "forma_de_pagamento_id"))
	private List<FormaDePagamento> formasDePagamento = new ArrayList<>();
		
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")	
	private OffsetDateTime dataCadastro;
		
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")	
	private OffsetDateTime dataAtualizacao;
	
	@OneToMany(mappedBy = "restaurante")	
	private List<Produto> produtos = new ArrayList<>();	
}
