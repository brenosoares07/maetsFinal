<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Cadastro de Produtos</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Produto</h1>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
            <input type="hidden" name="opcao" value="${opcao}"/>
            <input type="hidden" name="cod" value="${cod}"/>
            <p><label>Nome:</label> <input type="text" name="nome" value="${nome}" required/></p>
            <p><label>Preco de Venda:</label> <input type="number" step="0.01" name="precoVenda" value="${precoVenda}" required/></p>
            <p><label>Fabricante:</label> <input type="text" name="fabricante" value="${fabricante}"/></p>
            <p><label>Genero:</label> <input type="text" name="genero" value="${genero}"/></p>
            <p><label>Tipo:</label>
                <select name="codTipo">
                    <c:forEach var="t" items="${listaTipo}">
                        <c:choose>
                            <c:when test="${t.cod eq codTipo}">
                                <option selected value="${t.cod}">${t.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${t.cod}">${t.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>
            <input type="submit" value="Salvar"/>
        </form>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
            <input type="submit" value="Cancelar"/>
            <input type="hidden" name="opcao" value="cancelar"/>
        </form>
        <h2>${mensagem}</h2>
        <c:if test="${not empty listaProduto}">
            <table border="1">
                <tr><th>COD</th><th>NOME</th><th>PRECO</th><th>FABRICANTE</th><th>GENERO</th><th>TIPO</th><th>ALTERAR</th><th>EXCLUIR</th></tr>
                <c:forEach var="p" items="${listaProduto}">
                    <tr>
                        <td>${p.cod}</td><td>${p.nome}</td><td>R$${p.precoVenda}</td><td>${p.fabricante}</td><td>${p.genero}</td><td>${p.tipo.nome}</td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
                                <input type="hidden" name="opcao" value="enviarAlterar"/>
                                <input type="hidden" name="cod" value="${p.cod}"/>
                                <input type="hidden" name="nome" value="${p.nome}"/>
                                <input type="hidden" name="precoVenda" value="${p.precoVenda}"/>
                                <input type="hidden" name="fabricante" value="${p.fabricante}"/>
                                <input type="hidden" name="genero" value="${p.genero}"/>
                                <input type="hidden" name="codTipo" value="${p.tipo.cod}"/>
                                <button type="submit">Alterar</button>
                            </form>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
                                <input type="hidden" name="opcao" value="enviarExcluir"/>
                                <input type="hidden" name="cod" value="${p.cod}"/>
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
