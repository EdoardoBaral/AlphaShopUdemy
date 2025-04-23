package com.xantrix.webapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "BARCODE")
@Data
@RequiredArgsConstructor
public class BarCode {
	
	@Id
	@Column(name = "BARCODE")
	private String barCode;
	
	@Column(name = "IDTIPOART")
	private String idTipoArt;
	
	@ManyToOne
	@JoinColumn(name = "codart", referencedColumnName = "codart")
	private Articoli articolo;
}