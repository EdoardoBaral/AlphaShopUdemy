package com.xantrix.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
@RequiredArgsConstructor
public class LoginController {
	
	private final String titolo = "Accesso & Autenticazione";
	private final String sottotitolo = "Procedi ad inserire la userid e la password";
	private final String errmsg = "Spiacente, la userid o la password sono errati!";
	
	private final AuthenticationService authenticationService;
	
	@GetMapping(value="/login")
	public String getLogin(ModelMap model) {
		model.addAttribute("Titolo", titolo);
		model.addAttribute("SottoTitolo", sottotitolo);
		model.addAttribute("ErrMsg", errmsg);
		
		return "login";
	}
	
	@PostMapping(value="/login")
	public String gotoWelcomePage(@RequestParam("name") String name, @RequestParam("password") String password, ModelMap model) {
		if (authenticationService.auth(name, password)) {
			model.put("name", name);
			return "welcome";
		} else {
			return "redirect:/login?error=nologged";
		}
	}
}