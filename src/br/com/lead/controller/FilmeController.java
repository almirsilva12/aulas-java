package br.com.lead.controller;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.lead.modelo.Autor;
import br.com.lead.modelo.Filme;
import br.com.lead.util.JPAUtil;

@Controller
public class FilmeController {
	
	@RequestMapping(value="/persistir-filme", method=RequestMethod.GET)
	public String persistirFilme(String nome, String genero, String ano, String nomeAutor, String dtNascimento) {
		
		Filme filme = new Filme(nome == null ? "nome nulo" : nome, 
				genero == null ? "gênero nulo" : genero, 
				ano == null ? 0000 : Integer.parseInt(ano));
		
		Autor autor = new Autor();
		autor.setNome(nomeAutor);
		autor.setDataNascimento(LocalDate.parse(dtNascimento));
		filme.setAutor(autor);

		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(autor);
		em.persist(filme);
		em.getTransaction().commit();
		
		em.close();
		
		return "persistir-filme-view";
		
	}
	
	@RequestMapping(value = "/consultar-filme", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView consultarFilme(Integer id) {
		EntityManager entityManager = JPAUtil.getEntityManager();
	
	    Filme filme = entityManager.find(Filme.class, id);
	    
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("consultar-filme-view");
		modelAndView.addObject("nome", filme.getNome());
		modelAndView.addObject("genero", filme.getGenero());
		modelAndView.addObject("ano", filme.getAno());
	    
	    return modelAndView;
	}
}
