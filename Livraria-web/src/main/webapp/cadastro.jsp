<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="carrinho" scope="session" class="workshop.Carrinho"/>
<jsp:useBean id="cliente" scope="session" class="workshop.Cliente"/>
<jsp:useBean id="mensagem" scope="session" class="java.lang.String"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro</title>
<link href="css/estilo.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.css" rel="stylesheet">
</head>
<body>
<c:if test="${cliente.autenticacao == false }">
<%@ include file="mascara.html" %>
>

		<div class="container">
			<div class="panel panel-primary painel-cadastro">
				<div class="panel-heading">
					<span class="glyphicon glyphicon-user"></span>Cadastro
				</div>
				<div class="panel-body">


					<form method="post" action="EditarCliente?acao=cadastrar">
						<div class="row">

							${mensagem}
							<fieldset>


								<div class="form-group">
									<label for="nome">Nome Completo</label> <input type="text"
										class="form-control" id="nome" name="nome" autofocus required>
								</div>

								<div class="form-group">
									<label for="login">Usuário</label> <input type="text"
										class="form-control" id="login" name="login" required>
								</div>

								<div class="form-group">
									<label for="senha1">Senha</label> <input type="password"
										class="form-control" id="senha1" name="senha1" required>
								</div>

								<div class="form-group">
									<label for="senha2">Repita a senha</label> <input
										type="password" class="form-control" id="senha2" name="senha2"
										required>
								</div>


							</fieldset>
						</div>
						<input type="submit" class="btn btn-primary" value="Confirmar">
					</form>


				</div>
			</div>

		</div>

	</c:if>


	<c:if test="${cliente.autenticacao == true }">
		<%@ include file="masklog.html"%>
		<div class="container">

			<div class="panel panel-primary painel-cadastro">
				<div class="panel-heading">Editar Cadastro</div>
				<div class="panel-body">
					<form method="post" action="EditarCliente?acao=alterar">
						<div class="row">

							${mensagem}
							<fieldset>


								<div class="form-group">
									<label for="nome">Nome Completo</label> <input type="text"
										class="form-control" id="nome" name="nome" autofocus required>
								</div>

								<div class="form-group">
									<label for="login">Usuário</label> <input type="text"
										class="form-control" id="login" name="login" required>
								</div>

								<div class="form-group">
									<label for="senha1">Senha</label> <input type="password"
										class="form-control" id="senha1" name="senha1" required>
								</div>

								<div class="form-group">
									<label for="senha2">Repita a senha</label> <input
										type="password" class="form-control" id="senha2" name="senha2"
										required>
								</div>

								<div class="form-group">
									<label for="endereco">Endereço</label> <input type="text"
										class="form-control" id="endereco" name="endereco">
								</div>

								<div class="form-group">
									<label for="bairro">Bairro</label> <input type="text"
										class="form-control" id="bairro" name="bairro">
								</div>

								<div class="form-group">
									<label for="cidade">Cidade</label> <input type="text"
										class="form-control" id="cidade" name="cidade">
								</div>

								<div class="form-group">
									<label for="estado">Estado</label> <input type="text"
										class="form-control" id="estado" name="estado">
								</div>


								<div class="form-group">
									<label for="cep">CEP</label> <input type="text"
										class="form-control" id="cep" name="cep">
								</div>
							</fieldset>
						</div>
						<input type="submit" class="btn btn-primary" value="Confirmar">
						<c:if test="${cliente.autenticacao }">
							<c:if test="${carrinho.emptyCarrinho }">
								<a href="FinalizarCompra">Finalizar a compra</a>
							</c:if>
							<a href="VerificarPedidos">Vericar Pedidos</a>
						</c:if>
					</form>
				</div>
			</div>


		</div>
	</c:if>

	<%@ include file="rodape.html" %>
</body>
</html>
