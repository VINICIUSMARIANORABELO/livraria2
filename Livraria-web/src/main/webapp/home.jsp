<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<jsp:useBean id="cliente" scope="session" class="workshop.Cliente"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Arsenal Bookstore</title>
<link href="css/estilo.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${cliente.autenticacao == false }">
<%@ include file="mascara.html" %>

</c:if>
<c:if test="${cliente.autenticacao == true }">
<%@ include file="masklog.html" %>
</c:if>
<%@ include file="menu.html" %>
<img alt="Promoção" src="imagens/destaque-home.jpg" id="img-destaque">

<div class="container painel">
	
		<h2>Promoção</h2>
		<ol>
			<li>
				<a href="VerLivro?codigo=10">
					<figure>
						<img src="imagens/senhor.jpg">
						<figcaption>Senhor dos Anéis por R$ 169,00</figcaption>
					</figure>
				</a>
			</li>
			<li>
				<a href="VerLivro?codigo=5">
					<figure>
						<img src="imagens/casamento.jpg">
						<figcaption>O Casamento por R$39,90</figcaption>
					</figure>
				</a>
			</li>
			<li>
				<a href="VerLivro?codigo=12">
					<figure>
						<img src="imagens/lifeofpi.jpg">
						<figcaption>As Aventuras de Pi por R$23,50</figcaption>
					</figure>
				</a>
			</li>
			<li>
				<a href="VerLivro?codigo=13">
					<figure>
						<img src="imagens/onde.jpg">
						<figcaption>Para onde ela foi por R$20,00</figcaption>
					</figure>
				</a>
			</li>
			<li>
				<a href="VerLivro?codigo=9">
					<figure>
						<img src="imagens/submarinas.jpg">
						<figcaption>Vinte Mil Leguas Submarinas por R$14,90</figcaption>
					</figure>
				</a>
			</li>
			<li>
				<a href="VerLivro?codigo=15">
					<figure>
						<img src="imagens/sandman.jpg">
						<figcaption>The Ultimate Sandman a partir de R$400,00</figcaption>
					</figure>
				</a>
			</li>
			<li>
				<a href="VerLivro?codigo=16">
					<figure>
						<img src="imagens/watchmen.jpg">
						<figcaption>Watchmen por R$37,49</figcaption>
					</figure>
				</a>
			</li>
			<li>
				<a href="VerLivro?codigo=19">
					<figure>
						<img src="imagens/batman.jpeg">
						<figcaption>Batman por R$5,90</figcaption>
					</figure>
				</a>
			</li>
		</ol>
	
</div>

 

</body>
<%@ include file="rodape.html" %>
</html>