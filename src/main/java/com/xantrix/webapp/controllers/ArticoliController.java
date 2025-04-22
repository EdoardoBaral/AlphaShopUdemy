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

import java.util.List;

@Log
@Controller
@RequestMapping("/articoli")
@RequiredArgsConstructor
public class ArticoliController {
	
	private final ArticoliService articoliService;
	
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
}