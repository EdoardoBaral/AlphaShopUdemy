package com.xantrix.webapp.services;

import com.xantrix.webapp.dtos.ArticoliDto;

import java.util.List;

public interface ArticoliService {
	
	List<ArticoliDto> selAll();
	
	ArticoliDto selByCodArt(String codart);
	
	List<ArticoliDto> selByDescrizione(String filter, int page, int numrec);
	
	ArticoliDto selByBarcode(String barcode);
	
	int numRecords(String filter);
}
	