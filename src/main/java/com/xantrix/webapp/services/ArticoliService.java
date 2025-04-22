package com.xantrix.webapp.services;

import java.util.List;

import com.xantrix.webapp.dtos.ArticoloDto;
 
 
public interface ArticoliService 
{
	public List<ArticoloDto> SelAll();
	
	public ArticoloDto SelByCodArt(String codart);
	
	public List<ArticoloDto> SelByDescrizione(String filter, int page, int numrec);
	
	public ArticoloDto SelByBarcode(String barcode);
	
}
	