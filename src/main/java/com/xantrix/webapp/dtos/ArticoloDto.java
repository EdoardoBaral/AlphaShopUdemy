package com.xantrix.webapp.dtos;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class ArticoloDto {
	
	private String codart;
	private String descrizione;	
	private String um;
	private String codstat;
	private int pzcart;
	private double peso;
	private String status;
	private Date data;
	private double prezzo = 0;
	
	private Set<BarCodeDto> barcode = new HashSet<>();
	private IngredienteDto ingredienti;
	private CategoriaDto famAssort;
	private IvaDto iva;
}
