<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Cadastro de Servicos</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Servico</h1>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ServicosControlador">
            <input type="hidden" name="opcao" value="${opcao}"/>
            <input type="hidden" name="cod" value="${cod}"/>
            <p><label>Descricao:</label> <input type="text" name="descricao" value="${descricao}" required/></p>
            <p><label>Valor:</label> <input type="number" step="0.01" name="valor" value="${valor}" required/></p>
            <input type="submit" value="Salvar"/>
        </form>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ServicosControlador">
            <input type="submit" value="Cancelar"/>
            <input type="hidden" name="opcao" value="cancelar"/>
        </form>
        <h2>${mensagem}</h2>
        <c:if test="${not empty listaServicos}">
            <table border="1">
                <tr><th>COD</th><th>DESCRICAO</th><th>VALOR</th><th>ALTERAR</th><th>EXCLUIR</th></tr>
                <c:forEach var="s" items="${listaServicos}">
                    <tr>
                        <td>${s.cod}</td><td>${s.descricao}</td><td>R$${s.valor}</td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ServicosControlador">
                                <input type="hidden" name="opcao" value="enviarAlterar"/>
                                <input type="hidden" name="cod" value="${s.cod}"/>
                                <input type="hidden" name="descricao" value="${s.descricao}"/>
                                <input type="hidden" name="valor" value="${s.valor}"/>
                                <button type="submit">Alterar</button>
                            </form>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ServicosControlador">
                                <input type="hidden" name="opcao" value="enviarExcluir"/>
                                <input type="hidden" name="cod" value="${s.cod}"/>
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
