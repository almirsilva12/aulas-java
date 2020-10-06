package br.com.lead.controller;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.lead.modelo.Autor;
import br.com.lead.modelo.Filme;
import br.com.lead.util.JPAUtil;

@Controller
public class FilmeController {
	
	@Autowired
	AutorController autorController;
	
	@RequestMapping(value="/persistir-filme-json", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Filme persistirFilme(@RequestBody Filme filme) {
		EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
		
		try {
			autorController.consultarAutorJson(filme.getAutor().getId());
			em.persist(filme);
		} catch (Exception error) {
			em.persist(filme.getAutor());
			em.persist(filme);
		} finally {
			em.getTransaction().commit();
			em.close();
		}
		return filme;
	}
//	public String persistirFilmeJson(@RequestBody Filme filme) {
//		EntityManager em = JPAUtil.getEntityManager();
//
//		if ( filme.getAutor() == null) {
//			return "Insira um autor no objeto e tente novamente";
//		}
//		
//		if (filme.getAutor().getNome() == null || filme.getAutor().getNome().isEmpty()) {
//			return "autor sem nome";
//		}
//		em.getTransaction().begin();
//
//		if (filme.getAutor().getId() == null) {
//			em.persist(filme.getAutor());
//		} else {
//			Autor autorBanco = em.find(Autor.class, filme.getAutor().getId());
//			if (autorBanco == null) {
//				return "id do autor inválido";
//			} else {
//				filme.setAutor(autorBanco);
//			}
//		}
//		
//		em.persist(filme);
//		em.getTransaction().commit();
//		
//		em.close();
//		
//		return "Filme salvo com sucesso, id: " + filme.getId() + ", id do autor: "+ filme.getAutor().getId();
//		
//	}
//	
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
	
	@RequestMapping(value = "/consultar-filme-json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Filme consultarFilmeJson(Integer id) {
		EntityManager entityManager = JPAUtil.getEntityManager();
	
	    Filme filme = entityManager.find(Filme.class, id);

	    entityManager.close();
	    
	    return filme;
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
