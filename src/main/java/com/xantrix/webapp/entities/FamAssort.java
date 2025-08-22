package com.xantrix.webapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FAMASSORT")
@Getter
@Setter
@RequiredArgsConstructor
public class FamAssort {
	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "famAssort")
	private Set<Articoli> articoli = new HashSet<>();
}
