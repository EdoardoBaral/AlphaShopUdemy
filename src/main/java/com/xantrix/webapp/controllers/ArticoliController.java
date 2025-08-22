package com.xantrix.webapp.controllers;

import com.xantrix.webapp.dtos.ArticoliDto;
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
	
	List<PagingData> pages = new ArrayList<>();
	
	@GetMapping()
	public String getGestArt() {
		return "articoli";
	}
	
	@GetMapping(value = "/cerca/descrizione/{filter}")
	public String getArticoli(@PathVariable("filter") String filter,
							  @MatrixVariable(name = "page", defaultValue = "0") String page,
							  @MatrixVariable(name = "record", defaultValue = "10") String record,
							  ModelMap model) {
		int pageNum = Integer.parseInt(page); //Numero della pagina
		int recForPage = Integer.parseInt(record); //Record per pagina
		
		List<ArticoliDto> articoli = articoliService.selByDescrizione(filter, pageNum, recForPage);
		model.addAttribute("articoli", articoli);
		
		return "articoli";
	}
	
	@GetMapping(value = "/search")
	public String searchItem(@RequestParam(name = "filtro") String filtro,
							 @RequestParam(name = "selected", required = false, defaultValue = "10")  String selected,
							 ModelMap model) {
		log.info(String.format("Ricerca articoli con filtro %s ", filtro));
		
		int pageNum = 0;
		int recForPage = Integer.parseInt(selected);
		int numArt = 0;
		boolean notFound = false;
		
		List<ArticoliDto> articoli = new ArrayList<>();
		ArticoliDto articolo = articoliService.selByCodArt(filtro);
		
		if(articolo == null) {
			articolo = articoliService.selByBarcode(filtro);
			
			if(articolo == null) {
				articoli = articoliService.selByDescrizione(filtro, pageNum, recForPage);
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
		
		this.setPages(pageNum, numArt);
		
		model.addAttribute("articoli", articoli);
		model.addAttribute("PageNum", pageNum);
		model.addAttribute("RecPage", recForPage);
		model.addAttribute("filtro", filtro);
		model.addAttribute("Pages", pages);
		model.addAttribute("notFound", notFound);
		
		return "articoli";
	}
	
	// articoli/cerca/parametri;paging=0,10;exfilter=1,15?filtro=Barilla&selected=20
	@GetMapping(value = "/cerca/{parametri}")
	public String getArticoliWithPar(@MatrixVariable(pathVar = "parametri") Map<String, List<String>> parametri,
									 @RequestParam(name="filtro") String filtro,
									 ModelMap model) {
		int numArt = 0;
		int pageNum = 0;
		int recForPage = 10;
		
		//PARAMETRI PAGING
		List<String> paramPaging = parametri.get("paging");
		if(paramPaging != null) {
			try {
				pageNum = Integer.parseInt(paramPaging.get(0)); //Numero della pagina
				int diffPage = Integer.parseInt(paramPaging.get(2));
				
				if(pageNum >= 1) {
					pageNum += diffPage;
				} else {
					pageNum = 1;
				}
			} catch (NumberFormatException ex) {
				pageNum = 0;
				recForPage = 10;
			}
			
			log.info(String.format("pagina: %s, records %s", pageNum, recForPage));
		}
		
		//PARAMETRI FILTRI AGGIUNTIVI
		List<String> exFilter = parametri.get("exfilter");
		if(exFilter != null) {
			try {
				log.info(String.format("status: %s", exFilter.get(0)));
				log.info(String.format("categoria: %s", exFilter.get(1)));
			} catch (Exception ex) {
				log.info("Non sono stati passati parametri aggiuntivi");
			}
		}
		
		log.info("Cerco tutti gli articoli con descrizione " + filtro);
		
		List<ArticoliDto> articoli = articoliService.selByDescrizione(filtro, pageNum, recForPage);
		numArt = articoliService.numRecords(filtro);
		
		log.info(String.format("Trovati %s articoli", numArt));
		
		this.setPages(pageNum, numArt);
		
		model.addAttribute("articoli", articoli);
		model.addAttribute("PageNum", pageNum);
		model.addAttribute("RecPage", recForPage);
		model.addAttribute("filtro", filtro);
		model.addAttribute("Pages", pages);
		
		return "articoli";
	}
	
	//Metodo di creazione classi Pages
	private void setPages(int page, long numRecords) {
		int recForPage = 10;
		int min = 1;
		int valMin = 1;
		int max = 5;
		
		page = (page == 0) ? 1 : page;
		if(pages != null) {
			pages.clear();
		}
		
		int group = (int) Math.ceil((double)page / 5);
		max = group * 5;
		min = (max-5 == 0) ? 1 : (max-4);
		valMin = min;
		
		int maxPages = (numRecords > 0) ? (int) Math.ceil((double)numRecords / (double)recForPage) : 5;
		while (min <= max) {
			if(min > maxPages) {
				break;
			}
			
			pages.add(new PagingData(min,false));
			min++;
		}
		
		try {
			if(page - valMin > 0) {
				pages.get(page - valMin).setIsSelected(true);
			} else {
				pages.get(0).setIsSelected(true);
			}
		} catch (Exception ex) {
			pages.get(0).setIsSelected(true);
		}
	}
}
