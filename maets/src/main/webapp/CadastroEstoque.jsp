<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Cadastro de Estoque</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Estoque</h1>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/EstoqueControlador">
            <input type="hidden" name="opcao" value="${opcao}"/>
            <input type="hidden" name="cod" value="${cod}"/>
            <p><label>Produto:</label>
                <select name="codProduto">
                    <c:forEach var="p" items="${listaProduto}">
                        <c:choose>
                            <c:when test="${p.cod eq codProduto}">
                                <option selected value="${p.cod}">${p.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${p.cod}">${p.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>
            <p><label>Quantidade Atual:</label> <input type="number" name="quantidadeAtual" value="${quantidadeAtual}" required/></p>
            <input type="submit" value="Salvar"/>
        </form>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/EstoqueControlador">
            <input type="submit" value="Cancelar"/>
            <input type="hidden" name="opcao" value="cancelar"/>
        </form>
        <h2>${mensagem}</h2>
        <c:if test="${not empty listaEstoque}">
            <table border="1">
                <tr><th>COD</th><th>PRODUTO</th><th>QUANTIDADE</th><th>ALTERAR</th><th>EXCLUIR</th></tr>
                <c:forEach var="e" items="${listaEstoque}">
                    <tr>
                        <td>${e.cod}</td><td>${e.produto.nome}</td><td>${e.quantidadeAtual}</td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/EstoqueControlador">
                                <input type="hidden" name="opcao" value="enviarAlterar"/>
                                <input type="hidden" name="cod" value="${e.cod}"/>
                                <input type="hidden" name="quantidadeAtual" value="${e.quantidadeAtual}"/>
                                <input type="hidden" name="codProduto" value="${e.produto.cod}"/>
                                <button type="submit">Alterar</button>
                            </form>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/EstoqueControlador">
                                <input type="hidden" name="opcao" value="enviarExcluir"/>
                                <input type="hidden" name="cod" value="${e.cod}"/>
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
