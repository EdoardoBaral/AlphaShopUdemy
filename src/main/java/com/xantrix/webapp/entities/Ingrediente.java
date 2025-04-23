package com.xantrix.webapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INGREDIENTI")
@Getter
@Setter
@RequiredArgsConstructor
public class Ingrediente {
	
	@Id
	@Column(name = "CODART")
	private String codArt;
	
	@Column(name = "INFO")
	private String info;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Articolo articolo;
}