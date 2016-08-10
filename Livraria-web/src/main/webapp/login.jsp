<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="mensagem" scope="session" class="java.lang.String"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/estilo.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body bgcolor="#ffffff">
<%@ include file="mascara.html" %>
	<center>
	
	<form method="post" action="EditarCliente?acao=autenticar" class="container login">
		<pre class="login-form">
			<h1 id="login-msg">${mensagem}</h1>
			Usuário: <input type="text" name="login" id="login-nome" autofocus required/>
			Senha:<input type="password" name="senha" id="login-senha" required/>
			<input type="submit" value="Entrar"/>
			<a href="#">Esqueceu a senha?</a>
		</pre>
	<a>Novo no site?</a>
	<a href="EditarCliente?acao=verCadastro">Faça o cadastro aqui</a>
	</form>
	
	</center>
<% request.getSession().setAttribute("cliente", null); %>
<%@ include file="rodape.html" %>
</body>
</html>