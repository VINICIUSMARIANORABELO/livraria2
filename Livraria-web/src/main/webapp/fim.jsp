<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Transação Concluida</title>
<link href="css/estilo.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet" >
</head>
<body>
<%@include file="masklog.html" %>
<div class="container ">
<div class="panel painel-fim">
<h1 class="titulo">Pedido Incluido com Sucesso!!</h1>
<center>
<a href="VerificarPedidos" class="btn btn-primary btn-fim">Verificar Pedido</a>
</center>
</div>
</div>


<%@include file="rodape.html" %>

</body>
</html>