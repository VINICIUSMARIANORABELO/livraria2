<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/checkout.css" rel="stylesheet">

<title>Confirmar Pedidos</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="js/Pgto.js"></script>
<script type="text/javascript" src="js/CalcValor.js"></script>
</head>
<body>
	<header> <nav class=" navbar navbar-primary navbar-static-top">
	<div class="navbar-header">
		<a class="navbar-brand" href="home.jsp">TRIWAY Livraria</a>

		<button class="navbar-toggle" type="button"
			data-target="navbar-collapse" data-toggle="collapse">menu</button>
	</div>
	<c:if test="${cliente.autenticacao == false}">
		<ul class="nav navbar-nav collapse navbar-collapse navbar-right">
			<li><a href="#">Olá, Cliente</a></li>
			<li><a href="#"></a></li>
			<li><a href="login.jsp">Login</a></li>
			<li><a href="#"></a></li>
		</ul>
	</c:if> <c:if test="${cliente.autenticacao}">
		<ul class="nav navbar-nav collapse navbar-collapse navbar-right">
			<li><a href="#">Olá, ${cliente.nome }</a></li>
			<li><a href="#"></a></li>
			<li><a href="Logout">Sair</a></li>
			<li><a href="#"></a></li>
		</ul>
	</c:if> </nav>
	<style>
.navbar {
	margin: 0;
}
</style>
	</header>
	
	<c:if test="${carrinho.getItens().size() != 0 }">
		<div class="jumbotron">
			<div class="container">
				<h1>Ótima Escolha</h1>
				<p>Obrigado por comprar na TriWay Livraria! Por favor, confirme
					seus dados antes de efetivar a compra!</p>
			</div>
		</div>
		<div class="container cart">
			<div class="row">
				<div class="col-sm-12">

					<table class="table cart-items">
						<thead>
							<tr>
								<th class="produto">Produto</th>
								<th class="produto-preco">Preço</th>
								<th class="quantidade">Quantidade</th>
								<th class="preco-total">Total</th>
								<th class="item-remove"></th>
							</tr>
						</thead>
						<c:forEach items="${carrinho.itens }" var="item">
							<tbody>
								<tr class="produto-item">
									<td class="produto-image"><a
										href="VerLivro?codigo=${item.livro.codigo}"> <img
											src="${item.livro.imagem }">
									</a></td>
									<td class="produto-nome"><a
										href="VerLivro?codigo=${item.livro.codigo}">${item.livro.titulo}</a>
										<div class="autor">
											<span class="autor-nome">${item.livro.autor}</span>
										</div></td>
									<td class="produto-preco"><span>R$
											${item.livro.preco}0</span></td>
									<td class="quantidade">

										<form method="post" action="EditarCarrinho"
											name="form${item.livro.codigo}" id="form${item.livro.codigo}">
											<input type="hidden" id="ValorLivro" value="${item.livro.preco}">
											<input type="hidden" id="codigo"
												value="${item.livro.codigo}" />
											  
											<input type="number" id="QTD"
												name="qtd${item.livro.codigo}" value="${item.qtd}" min="1"
												max="20" onchange="CalcValor('form${item.livro.codigo}','item${item.livro.codigo}');"/>
										</form>

									</td>
									<td class="preco-total" id="item${item.livro.codigo}"><span>R$ ${item.valor2}</span></td>
									<td class="item-remove"><a
										href="EditarCarrinho?codigo=${item.livro.codigo}&acao=remover">
											<span class="icone-remove">X</span> <span
											class="hide item-remove-texto">Excluir Item</span>
									</a></td>
								</tr>
							</tbody>
						</c:forEach>
					</table>

				</div>
			</div>

		</div>

		<div class="container">
			<div class="forma-pagamento">
				<div class="row">
					<div class="col-md-8">
						<div class="panel panel-default">
							<div class="panel-body">
								<form method="post" action="FinalizarCompra">
									<legend>Forma de Pagamento</legend>

									<div class="radio">
										<label><input value="cartao" type="radio"
											name="optradio" checked="checked">Cartão de Crédito</label>
									</div>

									<div class="cartao box">
										<label>Cartão de Crédito</label>

										<div class="form-group">
											<label for="numero-cartao">Número - CVV</label> <input
												type="text" class="form-control" id="numero-cartao"
												name="numero-cartao">
										</div>

										<div class="form-group">
											<label for="bandeira-cartao">Bandeira</label> <select
												name="bandeira-cartao" id="bandeira-cartao"
												class="form-control">
												<option value="master">MasterCard</option>
												<option value="visa">Visa</option>
												<option value="amex">American Express</option>
											</select>
										</div>

										<div class="form-group">
											<label for="validade-cartao">Validade</label> <input
												type="month" class="form-control" id="validade-cartao"
												name="validade-cartao">
										</div>
									</div>

									<div class="radio">
										<label><input value="boleto" type="radio"
											name="optradio">Boleto Bancário (10% desc.)</label>
									</div>
									<div class="boleto box">
										<div class="form-group">
											<label for="numero-CPF">Informe seu CPF</label> <input
												type="text" class="form-control" id="numero-CPF"
												name="numero-CPF">
										</div>
									</div>
									<a href="Pesquisa" class="btn btn-primary">Continuar
										Comprando</a>
									<c:if test="${cliente.autenticacao}">
										<input type="submit" value="Finalizar Compra"
											class="btn btn-success">
									</c:if>
								</form>


							</div>
						</div>
					</div>
					<div class="col-md-4">
						<table class="table total-compra">
							<tbody>
								<tr>
									<td>Total dos Produtos</td>
									<td>R$ ${carrinho.valorString2}</td>
								</tr>
								<tr>
									<td>Descontos</td>
									<td>R$ 00,00</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td>TOTAL:</td>
									<td>R$ ${carrinho.valorString2}</td>
								</tr>
							</tfoot>
						</table>


					</div>
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${carrinho.getItens().size() == 0}">
		<div class="jumbotron">
			<div class="container">
				<h1>Carrinho Vazio</h1>
				<p>Adicione algum produto ao seu carrinho de compras para
					continuar!</p>
				<a href="Pesquisa" class="btn btn-primary">Continuar Comprando</a>
			</div>
		</div>
	</c:if>


</body>
<%@ include file="rodape.html"%>
</html>