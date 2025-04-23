package com.xantrix.webapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "IVA")
@Getter
@Setter
@RequiredArgsConstructor
public class Iva {
	
	@Id
	@Column(name = "IDIVA")
	private int idIva;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@Column(name = "ALIQUOTA")
	private int aliquota;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "iva")
	private Set<Articolo> articolo = new HashSet<>();
}