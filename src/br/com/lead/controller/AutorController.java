package br.com.lead.controller;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.lead.modelo.Autor;
import br.com.lead.util.JPAUtil;

@Controller
public class AutorController {

	@RequestMapping(value = "/consultar-autor", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView consultarAutor(@RequestParam Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		Autor autor = em.find(Autor.class, id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("consultar-autor-view");
		mav.addObject("nome", autor.getNome());
		mav.addObject("dataNascimento", autor.getDataNascimento());
		return mav;
	}
}
