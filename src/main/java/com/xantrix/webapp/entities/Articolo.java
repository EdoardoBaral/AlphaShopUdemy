package com.xantrix.webapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ARTICOLI")
@Getter
@Setter
@RequiredArgsConstructor
public class Articolo {
	
	@Id
	@Column(name = "codart")
	private String codArt;
	
	@Column(name = "descrizione")
	private String descrizione;	
	
	@Column(name = "um")
	private String um;
	
	@Column(name = "codstat")
	private String codStat;
	
	@Column(name = "pzcart")
	private Integer pzCart;
	
	@Column(name = "pesonetto")
	private double pesoNetto;
	
	@Column(name = "idstatoart")
	private String idStatoArt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datacreazione")
	private Date dataCreazione;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
	private Set<BarCode> barCodes = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
	private Ingrediente ingrediente;
	
	@ManyToOne
	@JoinColumn(name = "idiva",  referencedColumnName = "idiva")
	private Iva iva;
	
	@ManyToOne
	@JoinColumn(name = "idfamass", referencedColumnName = "id")
	private FamigliaAssortimento famigliaAssortimento;
}