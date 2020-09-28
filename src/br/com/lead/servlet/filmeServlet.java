package br.com.lead.servlet;

import br.com.lead.modelo.Filme;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/filme")
public class filmeServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Filme coringa =  new Filme("Coringa","Drama", 1990);
		Filme matrix =  new Filme("Matrix","Ação", 2000);
		Filme starWars =  new Filme("Star Wars","Fantasia", 1986);

		ArrayList<Filme> filmes = new ArrayList<Filme>();
		
		filmes.add(coringa);
		filmes.add(matrix);
		filmes.add(starWars);
		
		String genero = req.getParameter("genero");
		
		resp.setContentType("text/HTML");
		PrintWriter out = resp.getWriter();
		
		out.println("<h2> Catalogo de filmes do servlet </h2>");
		out.println("<ol>");
		
		if (genero == null) {
			filmes.stream().forEach(filme -> {
				out.println(String.format("<li>Nome: %s", filme.getNome()));
				out.println(String.format("Genero: %s", filme.getGenero()));
				out.println(String.format("Data: %d</li>", filme.getAno()));
			});
		} else {
			filmes.stream().filter(filme -> filme.getGenero().toUpperCase().equals(genero.toUpperCase())).forEach(filme -> {
				out.println(String.format("<li>Nome: %s", filme.getNome()));
				out.println(String.format("Genero: %s", filme.getGenero()));
				out.println(String.format("Data: %d</li>", filme.getAno()));
			});
		}
		
		out.println("</ol>");

	}
	
}
