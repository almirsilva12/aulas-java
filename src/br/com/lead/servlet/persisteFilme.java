package br.com.lead.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.lead.modelo.Autor;
import br.com.lead.modelo.Filme;
import br.com.lead.util.JPAUtil;

@WebServlet("/salvar-filme")
public class persisteFilme extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String ano = req.getParameter("ano");
		String genero = req.getParameter("genero");
		String nomeAutor = req.getParameter("nomeAutor");
		String dataNascimento = req.getParameter("dataNascimento");

		Autor autor = new Autor();
		autor.setNome(nomeAutor);
		autor.setDataNascimento(LocalDate.parse(dataNascimento));
		Filme filme = new Filme(nome, genero, Integer.parseInt(ano));
		filme.setAutor(autor);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(autor);
		em.persist(filme);
		em.getTransaction().commit();
		
		em.close();
		
	}


}
