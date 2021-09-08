package com.Loja.LojadGames.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_loja")
public class Loja {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size (min = 2, max = 200)
	private String genero;
	
	@NotNull
	@Size (min = 2, max = 100 )
	private String plataforma;

	
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	@JsonIgnoreProperties ("loja")	
	private List <Jogos> jogos;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	
	public List<Jogos> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogos> jogos) {
		this.jogos = jogos;
	}
}
