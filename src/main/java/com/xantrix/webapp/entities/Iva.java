package com.xantrix.webapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "IVA")
@Data
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
	private Set<Articoli> articoli = new HashSet<>();
}