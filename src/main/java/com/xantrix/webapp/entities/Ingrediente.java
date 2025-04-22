package com.xantrix.webapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "INGREDIENTI")
@Data
@RequiredArgsConstructor
public class Ingrediente {
	
	@Id
	@Column(name = "CODART")
	private String codArt;
	
	@Column(name = "INFO")
	private String info;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Articoli articolo;
}