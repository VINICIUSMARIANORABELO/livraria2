<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<jsp:useBean id="livro" class="workshop.Livro" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="css/estilo.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${livro.titulo }</title>
</head>
<body>
<c:if test="${cliente.autenticacao == false }">
<%@ include file="mascara.html" %>

</c:if>
<c:if test="${cliente.autenticacao == true }">
<%@ include file="masklog.html" %>
</c:if>
<br/>
<div class="container">
<div class="row">
        <div class="col-md-3">
        <%@ include file="menu.html" %>
        </div>
            <div class="col-lg-9">
<div class="row">
<div class="panel panel-default">
	<div class="panel-heading">
		<h2 class="panel-title">${livro.titulo}</h2>
	</div>
	<div class="panel-body">
	<div class="col-md-3">
		<img src="${livro.imagem }" class="img-thumbnail img-responsive" id="img-livro">
	</div>
	<div class="col-md-9">
		<dl>
			<dt class="titulo">Nome do Produto</dt>
			<dd class="descricao">${livro.titulo}</dd>
			
			<dt class="titulo">Autor</dt>
			<dd class="descricao">${livro.autor }</dd>
			
			<dt class="titulo">Preço: </dt>
			<dd class="descricao">R$ ${livro.preco }0</dd>
			
			<dt class="titulo">SIPNOSE</dt>
			<dd class="descricao">${livro.descricao }</dd>
		</dl>
	</div>
	</div>
</div>
<form action="EditarCarrinho" method="post">
	<input type="hidden" name="codigo" value="${livro.codigo}" /> 
	<input type="hidden" name="acao" value="adicionar" /> 
	<input type="submit" value="Adicionar aos Carrinhos" class="btn btn-success">
</form>

</div>
</div>
</div>
</div>
<%@ include file="rodape.html" %>

</body>
</html>