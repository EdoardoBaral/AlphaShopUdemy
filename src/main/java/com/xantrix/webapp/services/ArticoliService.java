package com.xantrix.webapp.services;

import java.util.List;

import com.xantrix.webapp.dtos.ArticoliDto;
 
 
public interface ArticoliService 
{
	public List<ArticoliDto> SelAll();
	
	public ArticoliDto SelByCodArt(String codart);
	
	public List<ArticoliDto> SelByDescrizione(String filter, int page, int numrec);
	
	public ArticoliDto SelByBarcode(String barcode);
	
}
	