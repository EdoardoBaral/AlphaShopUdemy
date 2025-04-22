package com.xantrix.webapp.services;

import com.xantrix.webapp.dtos.ArticoloDto;
import java.util.List;

public interface ArticoliService {
	
	List<ArticoloDto> selectAll();
	
	ArticoloDto selectByCodArt(String codart);
	
	List<ArticoloDto> selectByDescrizione(String filter, int page, int numrec);
	
	ArticoloDto selectByBarcode(String barcode);
}