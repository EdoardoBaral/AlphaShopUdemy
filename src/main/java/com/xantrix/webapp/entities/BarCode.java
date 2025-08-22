package com.xantrix.webapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BARCODE")
@Getter
@Setter
@RequiredArgsConstructor
public class Barcode {
	
	@Id
	@Column(name = "BARCODE")
	private String barcode;
	
	@Column(name = "IDTIPOART")
	private String idTipoArt;
	
	@ManyToOne
	@JoinColumn(name = "codart", referencedColumnName = "codart")
	private Articoli articolo;
}
