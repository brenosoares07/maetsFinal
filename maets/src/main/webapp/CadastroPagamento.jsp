<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Cadastro de Pagamentos</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Pagamento</h1>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador">
            <input type="hidden" name="opcao" value="${opcao}"/>
            <input type="hidden" name="cod" value="${cod}"/>
            <p><label>Metodo:</label> <input type="text" name="metodo" value="${metodo}" required/></p>
            <input type="submit" value="Salvar"/>
        </form>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador">
            <input type="submit" value="Cancelar"/>
            <input type="hidden" name="opcao" value="cancelar"/>
        </form>
        <h2>${mensagem}</h2>
        <c:if test="${not empty listaPagamento}">
            <table border="1">
                <tr><th>COD</th><th>METODO</th><th>ALTERAR</th><th>EXCLUIR</th></tr>
                <c:forEach var="p" items="${listaPagamento}">
                    <tr>
                        <td>${p.cod}</td><td>${p.metodo}</td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador">
                                <input type="hidden" name="opcao" value="enviarAlterar"/>
                                <input type="hidden" name="cod" value="${p.cod}"/>
                                <input type="hidden" name="metodo" value="${p.metodo}"/>
                                <button type="submit">Alterar</button>
                            </form>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador">
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
