package br.com.lead.servlet;

import br.com.lead.modelo.Filme;
import br.com.lead.util.JPAUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/info-filme")
public class infoFilmeServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		
		Filme filme = new Filme();
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		filme = em.find(Filme.class, 1);
		em.remove(filme);
		em.getTransaction().commit();
		em.close();

		/*req.setAttribute("filme", filme);
		RequestDispatcher dispatcher = req.getRequestDispatcher("infofilme.jsp");
		dispatcher.forward(req, resp);*/

	}
	
}
