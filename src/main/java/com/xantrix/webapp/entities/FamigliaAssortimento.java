package com.xantrix.webapp.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "FAMASSORT")
@Data
@RequiredArgsConstructor
public class FamigliaAssortimento {
	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "famigliaAssortimento")
	private Set<Articoli> articoli = new HashSet<>();
}