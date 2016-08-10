<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="pedidos" scope="session" class="java.util.ArrayList"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/estilo.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
<title>Pedidos</title>
</head>
<body>
<%@include file="masklog.html" %>
<form>
<center>
	<h1 class="titulo-pedido container"> Pedidos Realizados: </h1>
			<table class="table table-pedido container">
				<thead>
					<tr class="tr-superior">
						<th>Data</th>
						<th>Código</th>
						<th>QTD</th>
						<th>Pedido</th>
						<th>Pagamento</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pedidos}" var="pedido">
						<c:forEach items="${pedido.itens}" var="item">
							<tr class="tr-opcoes">
								<td><c:out value="${pedido.dataPedidoString}" /></td>
								<td><c:out value="${pedido.codigo}" /></td>
								<td><c:out value="${item.qtd}" /></td>
								<td><c:out value="${item.livro.titulo}" /></td>
								<td><c:out value="${pedido.pagamento}"/>
								<td><c:out value="${pedido.status}" /></td>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
		</center>

</form>
<%@include file="rodape.html" %>

</body>
</html>