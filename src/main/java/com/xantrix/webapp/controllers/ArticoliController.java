package com.xantrix.webapp.controllers;

import com.xantrix.webapp.dtos.ArticoloDto;
import com.xantrix.webapp.dtos.PagingData;
import com.xantrix.webapp.services.ArticoliService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@Controller
@RequestMapping("/articoli")
@RequiredArgsConstructor
public class ArticoliController {
	
	private final ArticoliService articoliService;
	private List<PagingData> pages = new ArrayList<>();
	
	@GetMapping()
	public String getGestioneArticoli() {
		return "articoli";
	}
	
	@GetMapping(value="/cerca/all")
	public String getArticoli(ModelMap model) {
		log.info("Selezioniamo tutti gli articoli");
		
		List<ArticoloDto> articoli = articoliService.selectAll();
		model.addAttribute("articoli", articoli);
		
		return "articoli";
	}
	
	@GetMapping(value="/cerca/descrizione/{filter}")
	public String getArticoli(@PathVariable("filter") String filter,
							  @MatrixVariable(name="page", defaultValue="0") String page,
							  @MatrixVariable(name="record", defaultValue="10") String record,
							  ModelMap model) {
		int pageNum = Integer.parseInt(page);
		int recordNum = Integer.parseInt(record);
		List<ArticoloDto> articoli = articoliService.selectByDescrizione(filter, pageNum, recordNum);
		model.addAttribute("articoli", articoli);
		
		return "articoli";
	}
	
	@GetMapping(value="/search")
	public String searchItem(@RequestParam("filtro") String filtro,
							 @RequestParam(required=false, defaultValue="10") String selected,
							 ModelMap model) {
		log.info(String.format("Ricerca articoli con filtro '%s'", filtro));
		
		int pageNum = 0;
		int recForPage = Integer.parseInt(selected);
		int numArt = 0;
		boolean notFound = false;
		ArticoloDto articolo = null;
		List<ArticoloDto> articoli = new ArrayList<>();
		
		articolo = articoliService.selectByCodArt(filtro);
		if(articolo == null) {
			articolo = articoliService.selectByBarcode(filtro);
			if(articolo == null) {
				articoli = articoliService.selectByDescrizione(filtro, pageNum, recForPage);
				numArt = articoliService.numRecords(filtro);
			} else {
				numArt = 1;
				articoli.add(articolo);
			}
		} else {
			numArt = 1;
			articoli.add(articolo);
		}
		
		log.info(String.format("Trovati %s articoli", numArt));
		
		if(articoli.isEmpty()) {
			notFound = true;
		}
		
		setPages(pageNum, numArt);
		
		model.addAttribute("articoli", articoli);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("recPage", recForPage);
		model.addAttribute("filtro", filtro);
		model.addAttribute("pages", pages);
		model.addAttribute("notFound", notFound);
		
		return "articoli";
	}
	
	// articoli/cerca/parametri;paging=0,10;exFilter=1,15?filter=Barilla
	@GetMapping(value="/cerca/{parametri}")
	public String getArticoliWithParam(@MatrixVariable(pathVar="parametri") Map<String, List<String>> parametri,
									   @RequestParam("filter") String filter,
									   ModelMap model) {
		int numArt = 0;
		int pageNum = 0;
		int recForPage = 10;
		
		List<String> paramPaging = parametri.get("page");
		if(paramPaging != null) {
			try {
				pageNum = Integer.parseInt(paramPaging.get(0));
				recForPage = Integer.parseInt(paramPaging.get(1));
				int diffPage = Integer.parseInt(paramPaging.get(2));
				
				if(pageNum >= 1) {
					pageNum += diffPage;
				} else {
					pageNum = 1;
				}
			} catch(NumberFormatException ex) {
				pageNum = 0;
				recForPage = 10;
			}
			
			log.info(String.format("Pagina: %s, record: %s", pageNum, recForPage));
		}
		
		List<String> exFilter = parametri.get("exFilter");
		if(exFilter != null) {
			try {
				log.info(String.format("Status: %s - Categoria: %s", exFilter.get(0), exFilter.get(1)));
			} catch(Exception ex) {
			
			}
		}
		
		log.info(String.format("Cerco tutti gli articoli con descrizione %s", filter));
		List<ArticoloDto> articoli = articoliService.selectByDescrizione(filter, pageNum, recForPage);
		numArt = articoliService.numRecords(filter);
		log.info(String.format("Trovati %s articoli", numArt));
		
		setPages(pageNum, numArt);
		
		model.addAttribute("articoli", articoli);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("recPage", recForPage);
		model.addAttribute("filtro", filter);
		model.addAttribute("pages", pages);
		
		return "articoli";
	}
	
	private void setPages(int page, long numRecords) {
		int recForPage = 10;
		int min = 1;
		int valMin = 1;
		int max = 5;
		
		page = (page == 0) ? 1 : page;
		
		if(pages != null) {
			pages.clear();
		}
		
		int group = (int) Math.ceil((double) page /5);
		max = group * 5;
		min = (max - 5) == 0 ? 1 : max - 4;
		valMin = min;
		
		int maxPages = (numRecords > 0) ? (int) Math.ceil((double) numRecords / (double) recForPage) : 5;
		while(min < max) {
			if(min > maxPages) {
				break;
			}
			
			pages.add(new PagingData(min, false));
			min++;
		}
		
		try {
			if(page - valMin > 0) {
				pages.get(page - valMin).setSelected(true);
			} else {
				pages.get(0).setSelected(true);
			}
		} catch(Exception ex) {
		
		}
	}
}