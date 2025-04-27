package com.xantrix.webapp.controllers;

import com.xantrix.webapp.dtos.ArticoloDto;
import com.xantrix.webapp.services.ArticoliService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Log
@Controller
@RequestMapping("/articoli")
@RequiredArgsConstructor
public class ArticoliController {
	
	private final ArticoliService articoliService;
	
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
	public String getArticoli(@PathVariable("filter") String filter, ModelMap model) {
		List<ArticoloDto> articoli = articoliService.selectByDescrizione(filter,0,10);
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
		
		model.addAttribute("articoli", articoli);
		model.addAttribute("filtro", filtro);
		model.addAttribute("notFound", notFound);
		
		return "articoli";
	}
}