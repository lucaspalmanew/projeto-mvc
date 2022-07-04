package com.lcp.projetos.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Receita {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String nome;
	
	@Column(length=2000)
	private String modoPreparo;
	
	private String comoServir;
	private int tempoPreparo;
	
	@ManyToMany
	private List<Ingredientes> ingredientes;
	
	@ManyToOne
	private UnidadeMedida unidadeMedida;

	public Receita() {

	}

	public Long getId() {
		return Id;
	}

	public void setId(Long i) {
		Id = i;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public String getComoServir(String string) {
		return comoServir;
	}

	public void setComoServir(String comoServir) {
		this.comoServir = comoServir;
	}

	public int getTempoPreparo() {
		return tempoPreparo;
	}

	public void setTempoPreparo(int i) {
		this.tempoPreparo = i;
	}

	public List<Ingredientes> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingredientes> string) {
		this.ingredientes = string;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
}
