<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/estilo.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
<title>Resultado</title>
</head>
<body>
<c:if test="${cliente.autenticacao == false }">
<%@ include file="mascara.html" %>

</c:if>
<c:if test="${cliente.autenticacao == true }">
<%@ include file="masklog.html" %>
</c:if>


        <div class="container painel-resultado">

        <!-- Page Heading -->
        
        <div class="row">
        <div class="col-md-3 painel-menu">
        <%@ include file="menu.html" %>
        </div>
            <div class="col-lg-9">
                <h1 class="page-header h1">Resultado
                    <small>Livros Disponíveis</small>
                </h1>
           
        <!-- /.row -->

        <!-- Project -->
        
        <c:forEach items="${livros }" var="livro">
        <div class="panel">
        <div class="row">
            <div class="col-md-3">
                <a href="VerLivro?codigo=${livro.codigo}">
                    <img class="thumbnail" src="${livro.imagem }" id="img-livro">
                </a>
            </div>
							<div class="col-md-9">
								<h3 class="titulo">${livro.titulo }</h3>
								<h4 class="pull-right preco">R$ ${livro.preco }0</h4>
								<h4 class="autor">${livro.autor }</h4>
								<p class="descricao">${livro.descricao }</p>
								
								<form action="EditarCarrinho" method="post">
									<input type="hidden" name="codigo" value="${livro.codigo}" />
									<input type="hidden" name="acao" value="adicionar" /> 
									<a class="btn btn-primary descricao" href="VerLivro?codigo=${livro.codigo}">Ver Produto</a>
									<input type="submit" value="Adicionar aos Carrinhos" class="btn btn-success">
								</form>
							</div>
						</div>
        </div>
        </c:forEach>
        <!-- /.row -->

        <hr>
         </div>
        </div>
</div>
      
    
    
<%@ include file="rodape.html" %>    
</body>
</html>