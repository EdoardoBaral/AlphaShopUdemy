package com.xantrix.webapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ArticoloDto {
	
	private String codArt;
	private String descrizione;	
	private String um;
	private String codStat;
	private int pzCart;
	private double peso;
	private String status;
	private Date dataCreazione;
	private double prezzo = 0;
	
	private Set<BarCodeDto> barCode = new HashSet<>();
	private IngredienteDto ingrediente;
	private CategoriaDto famigliaAssortimento;
	private IvaDto iva;
}