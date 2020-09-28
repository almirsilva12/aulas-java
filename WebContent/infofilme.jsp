<%@ page import="br.com.lead.modelo.Filme" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Informações do filme (Servlet)</title>
	</head>
	<body>
		
		<h2>Informações do filme (Servlet)</h2>
	
		<%
		Filme filme = new Filme("nome","genero", 1900);
		filme = (Filme) request.getAttribute("filme");
		
		out.println("<b>Nome:</b> "+filme.getNome()+"<br>");
		out.println("<b>Genêro:</b> "+filme.getGenero()+"<br>");
		out.println("<b>Ano:</b> "+filme.getAno()+"<br>");
		%>
	</body>
</html>