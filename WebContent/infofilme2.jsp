<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Informações do filme (Scriptlet)</title>
	</head>
	<body>
		<h2>Informações do filme (Scriptlet)</h2>
		<%
		String nome = request.getParameter("nome");
		String genero = request.getParameter("genero");
		Integer ano = Integer.parseInt(request.getParameter("ano"));
		
		out.println("<b>Nome:</b> "+nome+"<br>");
		out.println("<b>Gênero:</b> "+genero+"<br>");
		out.println("<b>Ano:</b> "+ano+"<br>");
		
		%>
	</body>
</html>