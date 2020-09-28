package br.com.lead.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringController {
	
	@RequestMapping(value="/ver-filme", method=RequestMethod.GET)
	@ResponseBody
	public String verFilme(String nome, String genero, Integer ano) {
		String body = nome + " " + genero + " " + ano; 
		return body;
	}
}
