package com.xantrix.webapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xantrix.webapp.dtos.ArticoliDto;
import com.xantrix.webapp.services.ArticoliService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/articoli")
public class ArticoliController 
{
	//@Autowired
	private ArticoliService articoliService;
	
	public ArticoliController(ArticoliService articoliService)
	{
		this.articoliService = articoliService;
	}
	
	@GetMapping(value="/cerca/all")
	public String getArticoli(ModelMap model)
	{
		log.info("Selezioniamo tutti gli articoli");
		
		List<ArticoliDto> articoli = articoliService.SelAll();
				//.stream().limit(20).collect(Collectors.toList());
				
		
		model.addAttribute("articoli", articoli);
		
		return "articoli";
	}
	
	@GetMapping(value="/cerca/descrizione/{filter}")
	public String GetArticoli(
			@PathVariable("filter") String filter,
			ModelMap model)
	{	
		List<ArticoliDto> articoli = articoliService.SelByDescrizione(filter,0,10);
		
		model.addAttribute("articoli", articoli);
		
		return "articoli";
		
	}
	
}
