package com.lampasw.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.lampasw.algafood.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
	private String nome;
		
	@Column(name = "taxa_frete", nullable = false)	
	private BigDecimal taxaFrete;
				
	//@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)	
	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne()
	@JoinColumn(name = "cozinha_id", nullable = false)	
	private Cozinha cozinha;
		
	@Embedded
	private Endereco endereco;
	
	private Boolean ativo = Boolean.TRUE;
	
	private Boolean aberto = Boolean.FALSE;
	
	@ManyToMany//fetch = FetchType.EAGER
	@JoinTable(name="restaurante_forma_de_pagamento",
			   joinColumns = @JoinColumn(name = "restaurante_id"),
			   inverseJoinColumns = @JoinColumn(name = "forma_de_pagamento_id"))
	private Set<FormaDePagamento> formasDePagamento = new HashSet<>();
	
	@ManyToMany//fetch = FetchType.EAGER
	@JoinTable(name="restaurante_usuario_responsavel",
			   joinColumns = @JoinColumn(name = "restaurante_id"),
			   inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>();
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")	
	private OffsetDateTime dataCadastro;
		
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")	
	private OffsetDateTime dataAtualizacao;
	
	@OneToMany(mappedBy = "restaurante")	
	private List<Produto> produtos = new ArrayList<>();
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}
	
	public void abrir() {
		setAberto(true);
	}
	
	public void fechar() {
		setAberto(false);
	}
	
	public boolean isAberto() {
	    return this.aberto;
	}

	public boolean isFechado() {
	    return !isAberto();
	}

	public boolean isInativo() {
	    return !isAtivo();
	}

	public boolean isAtivo() {
	    return this.ativo;
	}

	public boolean aberturaPermitida() {
	    return isAtivo() && isFechado();
	}

	public boolean ativacaoPermitida() {
	    return isInativo();
	}
	
	public boolean inativacaoPermitida() {
	    return isAtivo();
	}

	public boolean fechamentoPermitido() {
	    return isAberto();
	}      
	
	public boolean adicionarFormaDePagamento(FormaDePagamento formaDePagamento) {
		return getFormasDePagamento().add(formaDePagamento);
	}
	
	public boolean removerFormaDePagamento(FormaDePagamento formaDePagamento) {
		return getFormasDePagamento().remove(formaDePagamento);
	}
	
	public boolean adicionarResponsavel(Usuario usuario) {
		return getResponsaveis().add(usuario);
	}
	
	public boolean removerResponsavel(Usuario usuario) {
		return getResponsaveis().remove(usuario);
	}
	
	public boolean aceitaFormaDePagamento(FormaDePagamento formaDePagamento) {
		return getFormasDePagamento().contains(formaDePagamento);
	}
	
	public boolean naoAceitaFormaDePagamento(FormaDePagamento formaDePagamento) {
		return !aceitaFormaDePagamento(formaDePagamento);
	}
}
